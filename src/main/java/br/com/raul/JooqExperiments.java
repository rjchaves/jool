package br.com.raul;

import org.jooq.lambda.Seq;

import java.util.ArrayList;
import java.util.List;

public class JooqExperiments {
    private List<Foo> dummyList() {
        List<Foo> fooList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            fooList.add(new Foo());
        }
        return fooList;
    }

    public void fuckDesignPatterns() {
        List<Foo> fooList = dummyList();

        fooList = Seq.range(0, 30)
                .zip(fooList).map(tupla -> {
                    tupla.v2.setNumber(tupla.v1.toString());
                    return tupla.v2;
                }).toList();
        print(fooList);
    }

    private void print(List<Foo> fooList) {
        for (var foo : fooList) {
            System.out.println(foo.getNumber());
        }
    }

}
