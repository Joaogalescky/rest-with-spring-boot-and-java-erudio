package com.joaogalescky.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.joaogalescky.controllers.BookController;
import com.joaogalescky.controllers.PersonController;
import com.joaogalescky.data.vo.v1.BookVO;
import com.joaogalescky.data.vo.v1.PersonVO;
import com.joaogalescky.exceptions.RequiredObjectIsNullException;
import com.joaogalescky.exceptions.ResourceNotFoundException;
import com.joaogalescky.mapper.DozerMapper;
import com.joaogalescky.model.Book;
import com.joaogalescky.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;

	@Autowired
	PagedResourcesAssembler<BookVO> assembler;

	public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {
		//@formatter:off
		logger.info("Finding all books!");

		var booksPage = repository.findAll(pageable);

		var booksVOs = booksPage.map(p -> DozerMapper.parseObject(p, BookVO.class));
		booksVOs.map(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

		Link findAllLink = linkTo(
				methodOn(BookController.class).
				findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc"))
				.withSelfRel();
		return assembler.toModel(booksVOs, findAllLink);
		//@formatter:on
	}

	public PagedModel<EntityModel<BookVO>> findBooksByTitle(String title, Pageable pageable) {
		//@formatter:off
		logger.info("Finding book by title!");
		
		var bookPage = repository.findBooksByTitle(title, pageable);
		
		var bookVosPage = bookPage.map(b -> DozerMapper.parseObject(b, BookVO.class));
		bookVosPage.map(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		Link link = linkTo(methodOn(BookController.class).findAll(
				pageable.getPageNumber(), 
				pageable.getPageSize(), 
				"asc")).withSelfRel();
		return assembler.toModel(bookVosPage, link);
		//@formatter:on
	}

	public BookVO findById(Long id) {
		logger.info("Finding one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO book) {
		if (book == null)
			throw new RequiredObjectIsNullException();

		logger.info("Creating one book!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public BookVO update(BookVO book) {
		if (book == null)
			throw new RequiredObjectIsNullException();

		logger.info("Updating one book!");

		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}