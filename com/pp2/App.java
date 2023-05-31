package com.pp2;

public class App {
	private static int counter = 0;

	public static synchronized void increment() {
		++counter; //Proceso tiene su propia memoria
		//Hilos se ejecutan en una memoria compartida y los procesos en diferentes memorias.
	}
	
	//Sin sincronizacion, se vuelve a iniciar en 0
	
	public static void process() {

		Thread t1 = new Thread(new Runnable() { //Hilo1(Interfaz)

			@Override
			public void run() {
				for (int i = 0; i < 100; ++i)
					increment(); //Incrementar 1 a la variable Counter
			}
		});

		Thread t2 = new Thread(new Runnable() { //Hilo2 (Interfaz)

			@Override
			public void run() {
				for (int i = 0; i < 100; ++i)
					increment();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			//UNIR AMBOS HILOS
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		process();
		System.out.println("EL CONTADOR TOTAL ES: " +counter);
		
	}
}
