package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<>();
	int numero;

	@Override
	public void procurarPorNumero(int numero) {

		var conta = buscarNaCollection(numero);

		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\nA conta número: " + numero + " não foi encontrada!");

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número: " + conta.getNumero() + " foi criada com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());

		if (buscarConta != null) {

			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.println("\nA conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");

		} else
			System.out.println("\nA conta numero: " + conta.getNumero() + " não foi encontrada!");

	}

	@Override
	public void deletar(int numero) {
		
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true)
				System.out.println("\nA conta número: " + numero + " foi deletada com sucesso!");
		}else 
			System.out.println("\nA conta número: " + numero + " não foi encontrada!");
		

	}

	@Override
	public void sacar(int numero, float valor) {
		
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			
			if(conta.sacar(valor) == true)
				System.out.println("\nO saque na conta número: " + numero + " foi efetuada com sucesso!");
			
		}else 
			System.out.println("\nA conta numero: " + numero + " não foi encontrada!");

	}

	@Override
	public void depositar(int numero, float valor) {
		
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO depósito na conta número: " + numero + " foi efetuado com sucesso!");
				
		}else
			System.out.println("\nA conta número: " + numero + " não foi encontrada ou a conta destino não é uma conta corrente!");

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferência foi efetuada com sucesso!");
			}
		}else
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
			
	}

	public int gerarNumero() {
		return ++numero;
	}

	// Método auxiliar que busca uma conta na lista de contas pelo número da conta
	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

}
