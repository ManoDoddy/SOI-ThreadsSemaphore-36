package controller;

import java.util.concurrent.Semaphore;

public class CorredorThread extends Thread {
	
	private int tamanho = 200;
	private Semaphore semaforo;
	
	public CorredorThread(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		percorrer();
		try {
			semaforo.acquire();
			cruzar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void percorrer() {
		int velocidade = (int) (Math.random()*3) + 4;
		int total=0;
		do {
			try {
				sleep(1000);
				total+=velocidade;
				if(total>tamanho) {
					total = tamanho;
				}
				System.out.println("Pessoa #"+getId()+" percorreu "+total+" metros.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (total < tamanho);
	}
	
	private void cruzar() {
		int tempo = (int) (Math.random()*1001) + 1000;
		try {
			sleep(tempo);
			System.out.println("Pessoa #"+getId()+" cruzou a porta!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
