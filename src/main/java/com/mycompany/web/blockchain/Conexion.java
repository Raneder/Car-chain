package com.mycompany.web.blockchain;

//import java.lang.reflect.Constructor;
import java.math.BigInteger;

import java.io.IOException;
//import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.ClientConnectionException;
import org.web3j.protocol.http.HttpService;

import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;


public class Conexion {
    
	private static final String USER = "u0a0eo2bwg";
	private static final String PASS = "qkTghGk7yPA4eO9dhsjjm9GLairSZR1_dNT3Pj7ulqg";
	private static final String RPC_ENDPOINT = "https://u0nzn6nyo1-u0llst0yi7-rpc.us0-aws.kaleido.io/"; // With https://
	private static final BigInteger GAS_PRICE = BigInteger.valueOf(0); // connecting to zero gas price network
	private static final BigInteger GAS_LIMIT = BigInteger.valueOf(3000000);
    
    public Conexion(){

    }

    public String conectar(	String nombreC,	String DocumentoC,	String matriculaV,	String numeroP,	String estadoP,	 String fechaV){
        Logger appLogger = Logger.getLogger(Conexion.class.getName());

		try {
			// Build an Authorization header using your app credentials
			OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
			clientBuilder.authenticator(new Authenticator() {
				@Override
				public Request authenticate(Route route, Response response) throws IOException {
					String credential = Credentials.basic(USER, PASS);
					return response.request().newBuilder().header("Authorization", credential).build();
				}
			});
			// Create a Service object for web3 to connect to
			HttpService service = new HttpService(RPC_ENDPOINT, clientBuilder.build(), false);
			Web3j web3 = Web3j.build(service);

			// To test the connection, get the latest block in the chain
			EthBlock.Block latestBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
			System.out.format("Connected to Node: %s, Latest Block: %s\n", RPC_ENDPOINT, latestBlock.getNumber().toString());

			// Get the default account on the node, we will use that as the `from` address for sending transactions
			EthAccounts accounts = web3.ethAccounts().send();
			if (accounts.getAccounts().size() < 1) {
				System.out.println("Could not find accounts on the target node, unable to deploy contract");
				System.exit(-1);
			}
			String fromAddress = accounts.getAccounts().get(0);
			System.out.format("Using Node Account %s for transactions\n", fromAddress);

			// Create a transaction manager which is supplied to the smart contract provider
			TransactionManager tm = new ClientTransactionManager(web3, fromAddress);

			// Deploy the simple storage contract, use gas price and limit values above
			System.out.format("Deploying Simple Storage contract..\n");
			StaticGasProvider gp = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
			ContractCarChain_sol_simplestorage simpleStorage = ContractCarChain_sol_simplestorage.deploy(web3, tm, gp, nombreC, DocumentoC, matriculaV, numeroP, estadoP, fechaV).send();
			String ssContractAddress = simpleStorage.getContractAddress();
			System.out.format("Simple Storage contract address: %s\n", ssContractAddress);

			// Set a value in the contrac
			TransactionReceipt txReceipt = simpleStorage.set( nombreC, DocumentoC, matriculaV, numeroP, estadoP, fechaV).send();
			System.out.format("Set operation tx hash: %s\n", txReceipt.getTransactionHash());

			// Get the same value and make sure it's equal to what was set
			
		  /*String nombre = simpleStorage.getnombreCliente();
			String documento = simpleStorage.getDocumentoCliente().toString();
			String numero = simpleStorage.getnumeroPoliza().toString();
			String estado = simpleStorage.getestadoPoliza().toString();
			String matricula= simpleStorage.getmatriculaVehiculo().toString();
			String fechaVencimiento = simpleStorage.getfechaVencimiento().toString();
			ContratoInteligente CI= new ContratoInteligente(nombre, documento, numero, estado , matricula, fechaVencimiento, ssContractAddress);*/
        
			return ssContractAddress;
		} catch (IOException | ClientConnectionException ex) {
			// Catch any Connection errors and print them out
			appLogger.log(Level.SEVERE, null, ex);
            
            return null;
		} catch (Exception ex) {
			appLogger.log(Level.SEVERE, null, ex);
            
             return null;
		}
	} 

	    /*public String obteneContrato(String ssContractAddress ){
			Logger appLogger = Logger.getLogger(Conexion.class.getName());
	
			try {
				// Build an Authorization header using your app credentials
				OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
				clientBuilder.authenticator(new Authenticator() {
					@Override
					public Request authenticate(Route route, Response response) throws IOException {
						String credential = Credentials.basic(USER, PASS);
						return response.request().newBuilder().header("Authorization", credential).build();
					}
				});
				// Create a Service object for web3 to connect to
				HttpService service = new HttpService(RPC_ENDPOINT, clientBuilder.build(), false);
				Web3j web3 = Web3j.build(service);
	
				// To test the connection, get the latest block in the chain
				EthBlock.Block latestBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
				System.out.format("Connected to Node: %s, Latest Block: %s\n", RPC_ENDPOINT, latestBlock.getNumber().toString());
	
				// Get the default account on the node, we will use that as the `from` address for sending transactions
				EthAccounts accounts = web3.ethAccounts().send();
				if (accounts.getAccounts().size() < 1) {
					System.out.println("Could not find accounts on the target node, unable to deploy contract");
					System.exit(-1);
				}
				String fromAddress = accounts.getAccounts().get(0);
				System.out.format("Using Node Account %s for transactions\n", fromAddress);
	
				// Create a transaction manager which is supplied to the smart contract provider
				TransactionManager tm = new ClientTransactionManager(web3, fromAddress);
	
				// Deploy the simple storage contract, use gas price and limit values above
				System.out.format("Deploying Simple Storage contract..\n");
				StaticGasProvider gp = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
				//ContractCarChain_sol_simplestorage simpleStorage = ContractCarChain_sol_simplestorage.load(ssContractAddress, web3, tm, gp);
				System.out.format("Simple Storage contract address: %s\n", ssContractAddress);


				// Get the same value and make sure it's equal to what was set
		// 		String nombre = simpleStorage.getnombreCliente();
		// 		String documento = simpleStorage.getDocumentoCliente();
		// 		String numero = simpleStorage.getnumeroPoliza();
		// 		String estado = simpleStorage.getestadoPoliza();
		// 		String matricula= simpleStorage.getmatriculaVehiculo();
		// 		String fechaVencimiento = simpleStorage.getfechaVencimiento();
			// 	ContratoInteligente CI= new ContratoInteligente(nombre, documento, numero, estado , matricula, fechaVencimiento, ssContractAddress);
	
			return "Completado";
			} catch (IOException | ClientConnectionException ex) {
				// Catch any Connection errors and print them out
				appLogger.log(Level.SEVERE, null, ex);
				
				return "fallo";
			} catch (Exception ex) {
				appLogger.log(Level.SEVERE, null, ex);
				
				 return "fallo";
			} 



    	}*/

}
