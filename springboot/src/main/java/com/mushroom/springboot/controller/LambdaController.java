package com.mushroom.springboot.controller;

import com.mushroom.springboot.entity.Person;
import com.mushroom.springboot.service.MyLambdaInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@RestController
public class LambdaController {

    @GetMapping("/lambda")
    public String testLambda(){
        lambdaInterface();
        enact((s) -> System.out.println("Lambda:"+s),"NB");
        List<Person> personList = Arrays.asList(new Person("Li", "YaJiang", 18), new Person("Wang", "Ming", 20));
        checkAndExecute(personList,p -> p.getFristName().startsWith("L"),p -> System.out.println(p.getLastName()));
        personList.stream().filter(p -> p.getFristName().startsWith("W")).forEach(p -> System.out.println(p.getLastName()));
        personList.stream().filter(p -> p.getFristName().startsWith("L")).forEach(System.out::println);
        personList.stream().filter(p -> p.getFristName().startsWith("L")).forEach(LambdaController::methodRef);

        Person person = new Person("Lucy","DownTon",18);
        //Person person = null;

        Optional<Person> personOpt = Optional.ofNullable(person);
        personOpt.ifPresent(System.out::println);

        //return personOpt.orElse(new Person("Unknow","UnKnow",0)).toString();
        //return personOpt.orElseGet(() -> new Person("Intel","Yes",18)).toString();
        personOpt.orElseThrow(() -> new RuntimeException("Yes,Your Program have a Exception"));
        return personOpt.orElseGet(LambdaController::getPerson).toString();
    }

    public void lambdaInterface(){
        Consumer<String> myLambdaInterface = (s)->System.out.println("Result:"+s);
        myLambdaInterface.accept(String.valueOf(System.currentTimeMillis()));
    }

    public static void enact(MyLambdaInterface myLambdaInterface,String str){
        myLambdaInterface.doSomeThing(str);
    }

    public static void checkAndExecute(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer){
        personList.forEach(p -> {if(predicate.test(p)){consumer.accept(p);}});
        personList.stream().filter(p -> p.getFristName().startsWith("L")).forEach(p -> System.out.println(p.getLastName()));
    }
    public static void methodRef(Person person){
        System.out.println("年龄:"+person.getAge());
    }
    public static Person getPerson(){
        return new Person("AMD","Yes",28);
    }
}
