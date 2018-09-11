package com.java.controller;

import com.java.entity.Girl;
import com.java.exception.BusinessException;
import com.java.exception.ExceptionEnum;
import com.java.repository.GirlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/girl")
public class GirlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @PostMapping(path = "/girls")
    public Girl saveGirl(@Validated Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            LOGGER.error(bindingResult.getFieldError().getDefaultMessage());
        girl = girlRepository.save(girl);
        return girl;
    }

    @PostMapping(path = "/update/{id}")
    public Girl updateGirl(@PathVariable("id") Long ids, @RequestParam String name, @RequestParam Integer age) {
        Optional<Girl> byId = girlRepository.findById(ids);
        if (!byId.isPresent())
            throw new RuntimeException("信息未找到");
        Girl girl = girlRepository.findById(ids).get();
        girl.setName(name);
        girl.setAge(age);
        girl = girlRepository.save(girl);
        return girl;
    }

    @GetMapping("/delte")
    public boolean deleteGirl(@RequestParam("id") Long id) {
        girlRepository.deleteById(id);
        return true;
    }

    @GetMapping("/list")
    public Page<Girl> listGirls(@RequestParam("page") int page, @RequestParam int size) {
        LOGGER.info("in the listGirls");
        Sort sort = new Sort(Sort.Direction.DESC, "id").and(new Sort(Sort.Direction.DESC, "age"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Girl> girls = girlRepository.findAll(pageable);
        return girls;
    }

    @GetMapping("/testException/{id}")
    public void testException(@PathVariable("id") Long id) {
        Girl girl = girlRepository.getOne(id);
        if (girl.getAge() <= 10) {
            throw new BusinessException(ExceptionEnum.PRIMARY_SCHOOL);
        } else if (girl.getAge() <= 18) {
            throw new BusinessException(ExceptionEnum.MIDDLE_SCHOOL);
        }
    }
}
