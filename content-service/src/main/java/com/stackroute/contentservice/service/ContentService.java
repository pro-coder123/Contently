package com.stackroute.contentservice.service;

import com.stackroute.contentservice.exceptions.ContentAlreadyExistsExceptions;
import com.stackroute.contentservice.exceptions.ContentDoesNotExistException;
import com.stackroute.contentservice.exceptions.InternalServerErrorException;
import com.stackroute.contentservice.exceptions.NullValueFieldException;
import com.stackroute.contentservice.model.Content;

import java.util.List;
import java.util.Optional;


public interface ContentService {


    Content saveContent(Content content) throws ContentAlreadyExistsExceptions, NullValueFieldException,InternalServerErrorException;

   List<Content> findByTitle(String title) throws InternalServerErrorException;

   void deleteContent(int id) throws ContentDoesNotExistException,InternalServerErrorException;

   Content updateContent(Content content) throws ContentDoesNotExistException,InternalServerErrorException;

   int getNextSequence(String seq);
}
