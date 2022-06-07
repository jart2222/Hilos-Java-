package org.aguzman.hilos.ejemploexecutor;

import java.util.concurrent.*;

public class EjemploExecutorFurure {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Callable<String> tarea=()->{
            System.out.println("Inicio de tarea");
            try {
                System.out.println("Nombre del thread "+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("Finaliza la tarea.....");
            return "Algun resultado importante de la tarea";
        };

        Future<String> resultados = executor.submit(tarea);
        executor.shutdown();
        System.out.println("Continuando con la ejecucion del metodo main");

        //System.out.println(resultados.isDone());
        while (!resultados.isDone()){
            System.out.println("ejecutando tarea....");
            TimeUnit.MILLISECONDS.sleep(1500);
        }

        System.out.println("Obtenemos resultados de la tarea: "+resultados.get());
        System.out.println("Finaliza la tarea: "+resultados.isDone());

    }
}
