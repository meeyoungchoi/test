package com.example.test.controller;

import com.example.test.dto.IngredientForm;
import com.example.test.repository.IngredientRepository;
import com.example.test.vo.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    public String index(Model model) {
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        model.addAttribute("ingredientList", ingredientList);
        return "ingredients/index";
    }

    @GetMapping("/ingredients/new")
    public String newIngredient() {
        return "ingredients/new";
    }

    @PostMapping("/ingredients")
    public String create(IngredientForm form) {
        //질문 id가 null이 나오는 이유는 재료를 추가할떄 new,html에서 id값을 설정해주지 않았기 떄문인가?
        log.info(form.toString());

        Ingredient ingredient = form.toEntity();
        log.info(ingredient.toString());//질문 toString()를 오버라이딩 안했는데 사용할수 있는이유?

        Ingredient saved = ingredientRepository.save(ingredient);
        log.info(saved.toString());

        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        model.addAttribute("ingredient", ingredient);
        return "ingredients/edit";
    }


    @PostMapping("/ingredients/{id}")
    public String update(IngredientForm form) {
        log.info(form.toString());

        Ingredient ingredient = form.toEntity();
        log.info(ingredient.toString());

        Ingredient saved = ingredientRepository.save(ingredient);
        log.info(saved.toString());

        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/delete/{id}")
    public String delete(@PathVariable Integer id) {
        //질문 데이터를 전부삭제한 후 서버를 재시작하지 않고 새로운 데이터를 추가했을때 id가 마지막 번호 + 1이 되는이유?
        //객체가 삭제되어도 id는 id변수에 그대로 저장되어져 있어서 다음 데이터가 추가될때 + 1 되어지는것인가?
        ingredientRepository.deleteById(id);
        log.info(id +"번 삭제");
        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        model.addAttribute("ingredient", ingredient);
        return "ingredients/show";
    }

}
