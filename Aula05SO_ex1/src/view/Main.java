package view;

import java.util.concurrent.Semaphore;

import controller.ThreadGerenciaCruzamento;

public class Main {

	public static Semaphore semaforo;
	
	public static void main(String[] args) {
		int distCruzamento = 2000;
		int velCarro = 200;
		semaforo = new Semaphore(1);
		
		for (int sentido = 0; sentido < 4; sentido++) {
			Thread gCruzamento = new ThreadGerenciaCruzamento(distCruzamento, velCarro, sentido, semaforo);
			gCruzamento.start();
		}
	}

}
