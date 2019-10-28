package com.stackroute.contentservice.service;


import com.stackroute.contentservice.Sequence.Custom;
import com.stackroute.contentservice.exceptions.ContentAlreadyExistsExceptions;
import com.stackroute.contentservice.exceptions.ContentDoesNotExistException;
import com.stackroute.contentservice.exceptions.InternalServerErrorException;
import com.stackroute.contentservice.exceptions.NullValueFieldException;
import com.stackroute.contentservice.model.Content;
import com.stackroute.contentservice.repository.ContentRepository;
import com.stackroute.contentservice.service.ContentService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MongoOperations mongo;

    public Content saveContent(Content content) throws ContentAlreadyExistsExceptions,NullValueFieldException,InternalServerErrorException {

        try {
            Content u = contentRepository.save(content);
            return u;
        }catch (Exception e){
            throw new InternalServerErrorException();
        }

    }

   public List<Content> findByTitle(String title) throws InternalServerErrorException {

        try {
            return contentRepository.findByTitle(title);
        }
        catch(Exception ex){
            throw new InternalServerErrorException();
        }




    }

    @Override
    public void deleteContent(int id) throws ContentDoesNotExistException, InternalServerErrorException {
        try {
            contentRepository.deleteById(id);
        }catch (Exception e){
            throw new ContentDoesNotExistException();
        }
    }
    @Override
    public Content updateContent(Content user) throws ContentDoesNotExistException,InternalServerErrorException{
        if(contentRepository.findById(user.getId()).isPresent()){
            try{
                return contentRepository.save(user);
            }
            catch (Exception ex){
                throw new ContentDoesNotExistException();
            }

        }
        else {
            throw new ContentDoesNotExistException();
        }
    }


        public int getNextSequence(String seqName)
        {
            Custom counter = mongo.findAndModify(
                    query(where("_id").is(seqName)),
                    new Update().inc("seq",1),
                    options().returnNew(true).upsert(true),
                    Custom.class);
            return counter.getSeq();
        }

}
