package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/fruit", "/workintech/fruits"})
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public List<Fruit> getFruits() {
        return fruitService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Fruit> getFruitsDesc() {
        return fruitService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        return fruitService.getById(id);
    }

    @RequestMapping(value = "/name/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Fruit> searchByName(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

    @PostMapping("/{name}")
    public List<Fruit> searchByNameWithPost(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

    @PostMapping
    public Fruit save(@Valid @RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    @DeleteMapping("/{id}")
    public Fruit delete(@PathVariable Long id) {
        return fruitService.delete(id);
    }
}
