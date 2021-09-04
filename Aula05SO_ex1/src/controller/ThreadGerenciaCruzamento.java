package controller;

import java.util.concurrent.Semaphore;

public class ThreadGerenciaCruzamento extends Thread {
	
	private int distCruzamento, velCarro, recebeSentido;
	private Semaphore semaforo;
	private String sentido;
	
	public ThreadGerenciaCruzamento(int distCruzamento, int velCarro, int recebeSentido, Semaphore semaforo) {
		this.distCruzamento = distCruzamento;
		this.velCarro = velCarro;
		this.recebeSentido = recebeSentido;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		switch (recebeSentido) {
			case 0:
				sentido = "Esquerda";
				break;
			case 1:
				sentido = "Cima";
				break;
			case 2:
				sentido = "Direita";
				break;
			case 3:
				sentido = "Baixo";
				break;
		}
		carroAnda();
	}

	private void carroAnda() {
		int carroDist = 0;
		do {
			try {
				Thread.sleep(1000);
				carroDist += velCarro;
				if (carroDist > distCruzamento) {
					carroDist = distCruzamento;
					System.err.println("O carro que vai sentido a "+ sentido +" chegou no cruzamento!");
				}
				System.out.println("O carro que vai sentido a "+ sentido +" andou "+ carroDist +"km");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		} while(carroDist < distCruzamento);
		cruzamento();
	}

	private void cruzamento() {
		try {
			semaforo.acquire();
			passaCarroCruzamento();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void passaCarroCruzamento() {
		try {
			System.out.println("O carro que vai para "+ sentido +" está passando pelo cruzamento");
			Thread.sleep(3000);
			System.err.println("O carro que vai para "+ sentido +" passou pelo cruzamento");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
