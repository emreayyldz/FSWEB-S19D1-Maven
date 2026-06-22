package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/vegetable", "/workintech/vegetables"})
public class VegetableController {

    private final VegetableService vegetableService;

    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    @GetMapping
    public List<Vegetable> getVegetables() {
        return vegetableService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Vegetable> getVegetablesDesc() {
        return vegetableService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Vegetable getById(@PathVariable Long id) {
        return vegetableService.getById(id);
    }

    @RequestMapping(value = "/name/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Vegetable> searchByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }

    @PostMapping("/{name}")
    public List<Vegetable> searchByNameWithPost(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }

    @PostMapping
    public Vegetable save(@Valid @RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    @DeleteMapping("/{id}")
    public Vegetable delete(@PathVariable Long id) {
        return vegetableService.delete(id);
    }
}
