package io.lao.alloutjpa.service;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(Aklat aklat){
        bookRepository.save(aklat);
    }


    @Override
    public List<Aklat> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Aklat> findAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Aklat> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Aklat> findAllById(Iterable<Integer> id) {
        return bookRepository.findAllById(id);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void delete(Aklat aklat) {
        bookRepository.delete(aklat);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> id) {
        bookRepository.deleteAllById(id);
    }

    @Override
    public void deleteAll(Iterable<? extends Aklat> aklats) {
        bookRepository.deleteAll(aklats);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public <S extends Aklat> S save(S aklat) {
        return bookRepository.save(aklat);
    }

    @Override
    public <S extends Aklat> List<S> saveAll(Iterable<S> aklat) {
        return bookRepository.saveAll(aklat);
    }

    @Override
    public Optional<Aklat> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return bookRepository.existsById(id);
    }

    @Override
    public void flush() {
        bookRepository.flush();
    }

    @Override
    public <S extends Aklat> S saveAndFlush(S aklat) {
        return bookRepository.saveAndFlush(aklat);
    }

    @Override
    public <S extends Aklat> List<S> saveAllAndFlush(Iterable<S> aklat) {
        return bookRepository.saveAllAndFlush(aklat);
    }

    @Override
    public void deleteAllInBatch(Iterable<Aklat> aklat) {
        bookRepository.deleteAllInBatch(aklat);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> id) {
        bookRepository.deleteAllByIdInBatch(id);
    }

    @Override
    public void deleteAllInBatch() {
        bookRepository.deleteAllInBatch();
    }

    @Override
    public Aklat getOne(Integer id ) {
        return bookRepository.getOne(id);
    }

    @Override
    public Aklat getById(Integer id) {
        return bookRepository.getById(id);
    }

    @Override
    public <S extends Aklat> Optional<S> findOne(Example<S> example) {
        return bookRepository.findOne(example);
    }

    @Override
    public <S extends Aklat> List<S> findAll(Example<S> example) {
        return bookRepository.findAll(example);
    }

    @Override
    public <S extends Aklat> List<S> findAll(Example<S> example, Sort sort) {
        return bookRepository.findAll(example, sort);
    }

    @Override
    public <S extends Aklat> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bookRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Aklat> long count(Example<S> example) {
        return bookRepository.count(example);
    }

    @Override
    public <S extends Aklat> boolean exists(Example<S> example) {
        return bookRepository.exists(example);
    }

    @Override
    public <S extends Aklat, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bookRepository.findBy(example, queryFunction);
    }
}
