package br.com.raul;

import org.jooq.lambda.Seq;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JooqExperiments {
    private Long SIZE = 10000000L;

    private Instant getCurrentTime() {
        return Instant
                .now()
                .truncatedTo(
                        ChronoUnit.MICROS
                );
    }

    private List<Foo> dummyList() {
        List<Foo> fooList = new ArrayList();
        for (long i = 0; i < SIZE; i++) {
            fooList.add(new Foo());
        }
        return fooList;
    }

    public void testarRange() {
        System.out.println("Rodando: range");
        List<Foo> fooList = dummyList();
        long inicio = Calendar.getInstance().getTimeInMillis();

        Seq.range(0, SIZE)
                .zip(fooList).map(tupla -> {
                    tupla.v2.setNumber(tupla.v1.toString());
                    return tupla.v2;
                }).toList();

        long fim = Calendar.getInstance().getTimeInMillis();

        System.out.println("Tempo: " + (fim - inicio));
    }
    private void testarFor() {
        System.out.println("Rodando: for");
        List<Foo> fooList = dummyList();
        long inicio = Calendar.getInstance().getTimeInMillis();

        for(Integer i = 0; i < SIZE; i++) {
            fooList.get(i).setNumber(i.toString());
        }

        long fim = Calendar.getInstance().getTimeInMillis();

        System.out.println("Tempo: " + (fim - inicio));
    }

    public void fuckDesignPatterns() {
        testarRange();
        testarFor();
    }
}
