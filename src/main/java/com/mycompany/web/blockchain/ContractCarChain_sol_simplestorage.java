package com.mycompany.web.blockchain;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class ContractCarChain_sol_simplestorage extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200189438038062001894833981810160405281019062000037919062000242565b8560009081620000489190620005e3565b5084600190816200005a9190620005e3565b5083600290816200006c9190620005e3565b5082600390816200007e9190620005e3565b508160049081620000909190620005e3565b508060059081620000a29190620005e3565b50505050505050620006ca565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6200011882620000cd565b810181811067ffffffffffffffff821117156200013a5762000139620000de565b5b80604052505050565b60006200014f620000af565b90506200015d82826200010d565b919050565b600067ffffffffffffffff82111562000180576200017f620000de565b5b6200018b82620000cd565b9050602081019050919050565b60005b83811015620001b85780820151818401526020810190506200019b565b60008484015250505050565b6000620001db620001d58462000162565b62000143565b905082815260208101848484011115620001fa57620001f9620000c8565b5b6200020784828562000198565b509392505050565b600082601f830112620002275762000226620000c3565b5b815162000239848260208601620001c4565b91505092915050565b60008060008060008060c08789031215620002625762000261620000b9565b5b600087015167ffffffffffffffff811115620002835762000282620000be565b5b6200029189828a016200020f565b965050602087015167ffffffffffffffff811115620002b557620002b4620000be565b5b620002c389828a016200020f565b955050604087015167ffffffffffffffff811115620002e757620002e6620000be565b5b620002f589828a016200020f565b945050606087015167ffffffffffffffff811115620003195762000318620000be565b5b6200032789828a016200020f565b935050608087015167ffffffffffffffff8111156200034b576200034a620000be565b5b6200035989828a016200020f565b92505060a087015167ffffffffffffffff8111156200037d576200037c620000be565b5b6200038b89828a016200020f565b9150509295509295509295565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620003eb57607f821691505b602082108103620004015762000400620003a3565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026200046b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826200042c565b6200047786836200042c565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b6000620004c4620004be620004b8846200048f565b62000499565b6200048f565b9050919050565b6000819050919050565b620004e083620004a3565b620004f8620004ef82620004cb565b84845462000439565b825550505050565b600090565b6200050f62000500565b6200051c818484620004d5565b505050565b5b8181101562000544576200053860008262000505565b60018101905062000522565b5050565b601f82111562000593576200055d8162000407565b62000568846200041c565b8101602085101562000578578190505b6200059062000587856200041c565b83018262000521565b50505b505050565b600082821c905092915050565b6000620005b86000198460080262000598565b1980831691505092915050565b6000620005d38383620005a5565b9150826002028217905092915050565b620005ee8262000398565b67ffffffffffffffff8111156200060a5762000609620000de565b5b620006168254620003d2565b6200062382828562000548565b600060209050601f8311600181146200065b576000841562000646578287015190505b620006528582620005c5565b865550620006c2565b601f1984166200066b8662000407565b60005b8281101562000695578489015182556001820191506020850194506020810190506200066e565b86831015620006b55784890151620006b1601f891682620005a5565b8355505b6001600288020188555050505b505050505050565b6111ba80620006da6000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c80637677d39b1161008c578063c1789d4111610066578063c1789d411461022d578063c6f6b9cc1461024b578063f2fd451014610269578063f9a0987014610287576100ea565b80637677d39b146101d35780638ff19140146101f1578063a57cc3901461020f576100ea565b80632d7dccba116100c85780632d7dccba1461015b57806333d020fc146101795780634c478e4c146101975780636dd7071d146101b5576100ea565b806316d936fb146100ef57806323e166441461011f5780632c46b2051461013d575b600080fd5b61010960048036038101906101049190610c3b565b6102a5565b6040516101169190610def565b60405180910390f35b61012761038f565b6040516101349190610def565b60405180910390f35b610145610421565b6040516101529190610def565b60405180910390f35b6101636104b3565b6040516101709190610def565b60405180910390f35b610181610541565b60405161018e9190610def565b60405180910390f35b61019f6105cf565b6040516101ac9190610def565b60405180910390f35b6101bd610661565b6040516101ca9190610def565b60405180910390f35b6101db6106f3565b6040516101e89190610def565b60405180910390f35b6101f9610785565b6040516102069190610def565b60405180910390f35b610217610813565b6040516102249190610def565b60405180910390f35b6102356108a1565b6040516102429190610def565b60405180910390f35b610253610933565b6040516102609190610def565b60405180910390f35b6102716109c1565b60405161027e9190610def565b60405180910390f35b61028f610a53565b60405161029c9190610def565b60405180910390f35b606086600090816102b69190611027565b507f1a3ae04c151d80be69352a9643ea8823e3ef22d99b82a2103660d66e8922b0ad8787878787876040516102f0969594939291906110f9565b60405180910390a16000805461030590610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461033190610e40565b801561037e5780601f106103535761010080835404028352916020019161037e565b820191906000526020600020905b81548152906001019060200180831161036157829003601f168201915b505050505090509695505050505050565b60606003805461039e90610e40565b80601f01602080910402602001604051908101604052809291908181526020018280546103ca90610e40565b80156104175780601f106103ec57610100808354040283529160200191610417565b820191906000526020600020905b8154815290600101906020018083116103fa57829003601f168201915b5050505050905090565b60606003805461043090610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461045c90610e40565b80156104a95780601f1061047e576101008083540402835291602001916104a9565b820191906000526020600020905b81548152906001019060200180831161048c57829003601f168201915b5050505050905090565b600480546104c090610e40565b80601f01602080910402602001604051908101604052809291908181526020018280546104ec90610e40565b80156105395780601f1061050e57610100808354040283529160200191610539565b820191906000526020600020905b81548152906001019060200180831161051c57829003601f168201915b505050505081565b6005805461054e90610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461057a90610e40565b80156105c75780601f1061059c576101008083540402835291602001916105c7565b820191906000526020600020905b8154815290600101906020018083116105aa57829003601f168201915b505050505081565b6060600080546105de90610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461060a90610e40565b80156106575780601f1061062c57610100808354040283529160200191610657565b820191906000526020600020905b81548152906001019060200180831161063a57829003601f168201915b5050505050905090565b60606001805461067090610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461069c90610e40565b80156106e95780601f106106be576101008083540402835291602001916106e9565b820191906000526020600020905b8154815290600101906020018083116106cc57829003601f168201915b5050505050905090565b60606004805461070290610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461072e90610e40565b801561077b5780601f106107505761010080835404028352916020019161077b565b820191906000526020600020905b81548152906001019060200180831161075e57829003601f168201915b5050505050905090565b6001805461079290610e40565b80601f01602080910402602001604051908101604052809291908181526020018280546107be90610e40565b801561080b5780601f106107e05761010080835404028352916020019161080b565b820191906000526020600020905b8154815290600101906020018083116107ee57829003601f168201915b505050505081565b6003805461082090610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461084c90610e40565b80156108995780601f1061086e57610100808354040283529160200191610899565b820191906000526020600020905b81548152906001019060200180831161087c57829003601f168201915b505050505081565b6060600580546108b090610e40565b80601f01602080910402602001604051908101604052809291908181526020018280546108dc90610e40565b80156109295780601f106108fe57610100808354040283529160200191610929565b820191906000526020600020905b81548152906001019060200180831161090c57829003601f168201915b5050505050905090565b6002805461094090610e40565b80601f016020809104026020016040519081016040528092919081815260200182805461096c90610e40565b80156109b95780601f1061098e576101008083540402835291602001916109b9565b820191906000526020600020905b81548152906001019060200180831161099c57829003601f168201915b505050505081565b6060600280546109d090610e40565b80601f01602080910402602001604051908101604052809291908181526020018280546109fc90610e40565b8015610a495780601f10610a1e57610100808354040283529160200191610a49565b820191906000526020600020905b815481529060010190602001808311610a2c57829003601f168201915b5050505050905090565b60008054610a6090610e40565b80601f0160208091040260200160405190810160405280929190818152602001828054610a8c90610e40565b8015610ad95780601f10610aae57610100808354040283529160200191610ad9565b820191906000526020600020905b815481529060010190602001808311610abc57829003601f168201915b505050505081565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610b4882610aff565b810181811067ffffffffffffffff82111715610b6757610b66610b10565b5b80604052505050565b6000610b7a610ae1565b9050610b868282610b3f565b919050565b600067ffffffffffffffff821115610ba657610ba5610b10565b5b610baf82610aff565b9050602081019050919050565b82818337600083830152505050565b6000610bde610bd984610b8b565b610b70565b905082815260208101848484011115610bfa57610bf9610afa565b5b610c05848285610bbc565b509392505050565b600082601f830112610c2257610c21610af5565b5b8135610c32848260208601610bcb565b91505092915050565b60008060008060008060c08789031215610c5857610c57610aeb565b5b600087013567ffffffffffffffff811115610c7657610c75610af0565b5b610c8289828a01610c0d565b965050602087013567ffffffffffffffff811115610ca357610ca2610af0565b5b610caf89828a01610c0d565b955050604087013567ffffffffffffffff811115610cd057610ccf610af0565b5b610cdc89828a01610c0d565b945050606087013567ffffffffffffffff811115610cfd57610cfc610af0565b5b610d0989828a01610c0d565b935050608087013567ffffffffffffffff811115610d2a57610d29610af0565b5b610d3689828a01610c0d565b92505060a087013567ffffffffffffffff811115610d5757610d56610af0565b5b610d6389828a01610c0d565b9150509295509295509295565b600081519050919050565b600082825260208201905092915050565b60005b83811015610daa578082015181840152602081019050610d8f565b60008484015250505050565b6000610dc182610d70565b610dcb8185610d7b565b9350610ddb818560208601610d8c565b610de481610aff565b840191505092915050565b60006020820190508181036000830152610e098184610db6565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610e5857607f821691505b602082108103610e6b57610e6a610e11565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302610ed37fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610e96565b610edd8683610e96565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b6000610f24610f1f610f1a84610ef5565b610eff565b610ef5565b9050919050565b6000819050919050565b610f3e83610f09565b610f52610f4a82610f2b565b848454610ea3565b825550505050565b600090565b610f67610f5a565b610f72818484610f35565b505050565b5b81811015610f9657610f8b600082610f5f565b600181019050610f78565b5050565b601f821115610fdb57610fac81610e71565b610fb584610e86565b81016020851015610fc4578190505b610fd8610fd085610e86565b830182610f77565b50505b505050565b600082821c905092915050565b6000610ffe60001984600802610fe0565b1980831691505092915050565b60006110178383610fed565b9150826002028217905092915050565b61103082610d70565b67ffffffffffffffff81111561104957611048610b10565b5b6110538254610e40565b61105e828285610f9a565b600060209050601f831160018114611091576000841561107f578287015190505b611089858261100b565b8655506110f1565b601f19841661109f86610e71565b60005b828110156110c7578489015182556001820191506020850194506020810190506110a2565b868310156110e457848901516110e0601f891682610fed565b8355505b6001600288020188555050505b505050505050565b600060c08201905081810360008301526111138189610db6565b905081810360208301526111278188610db6565b9050818103604083015261113b8187610db6565b9050818103606083015261114f8186610db6565b905081810360808301526111638185610db6565b905081810360a08301526111778184610db6565b905097965050505050505056fea264697066735822122070bc933a0c16c2cbe01cf9ec0c8063e490cf766226b43fd7cc1961529292cc0e64736f6c63430008110033";

    public static final String FUNC_DOCUMENTOCLIENTE = "DocumentoCliente";

    public static final String FUNC_ESTADOPOLIZA = "estadoPoliza";

    public static final String FUNC_FECHAVENCIMIENTO = "fechaVencimiento";

    public static final String FUNC_GETDOCUMENTOCLIENTE = "getDocumentoCliente";

    public static final String FUNC_GETESTADOPOLIZA = "getestadoPoliza";

    public static final String FUNC_GETFECHAVENCIMIENTO = "getfechaVencimiento";

    public static final String FUNC_GETMATRICULAVEHICULO = "getmatriculaVehiculo";

    public static final String FUNC_GETNOMBRECLIENTE = "getnombreCliente";

    public static final String FUNC_GETNUMEROPOLIZA = "getnumeroPoliza";

    public static final String FUNC_MATRICULAVEHICULO = "matriculaVehiculo";

    public static final String FUNC_NOMBRECLIENTE = "nombreCliente";

    public static final String FUNC_NUMEROPOLIZA = "numeroPoliza";

    public static final String FUNC_QUERY = "query";

    public static final String FUNC_SET = "set";

    public static final Event DATASTORED_EVENT = new Event("DataStored", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ContractCarChain_sol_simplestorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractCarChain_sol_simplestorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractCarChain_sol_simplestorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractCarChain_sol_simplestorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DataStoredEventResponse> getDataStoredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DATASTORED_EVENT, transactionReceipt);
        ArrayList<DataStoredEventResponse> responses = new ArrayList<DataStoredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DataStoredEventResponse typedResponse = new DataStoredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.nombreCliente = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.DocumentoCliente = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.matriculaVehiculo = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.numeroPoliza = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.estadoPoliza = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.fechaVencimiento = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DataStoredEventResponse> dataStoredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DataStoredEventResponse>() {
            @Override
            public DataStoredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DATASTORED_EVENT, log);
                DataStoredEventResponse typedResponse = new DataStoredEventResponse();
                typedResponse.log = log;
                typedResponse.nombreCliente = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.DocumentoCliente = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.matriculaVehiculo = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.numeroPoliza = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.estadoPoliza = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.fechaVencimiento = (String) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DataStoredEventResponse> dataStoredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DATASTORED_EVENT));
        return dataStoredEventFlowable(filter);
    }

    public RemoteFunctionCall<String> DocumentoCliente() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DOCUMENTOCLIENTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> estadoPoliza() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ESTADOPOLIZA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> fechaVencimiento() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FECHAVENCIMIENTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getDocumentoCliente() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDOCUMENTOCLIENTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getestadoPoliza() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETESTADOPOLIZA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getfechaVencimiento() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETFECHAVENCIMIENTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getmatriculaVehiculo() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMATRICULAVEHICULO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getnombreCliente() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNOMBRECLIENTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getnumeroPoliza() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMEROPOLIZA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> matriculaVehiculo() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MATRICULAVEHICULO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> nombreCliente() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NOMBRECLIENTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> numeroPoliza() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NUMEROPOLIZA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> query() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_QUERY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> set(String nombreC, String DocumentoC, String matriculaV, String numeroP, String estadoP, String fechaV) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nombreC), 
                new org.web3j.abi.datatypes.Utf8String(DocumentoC), 
                new org.web3j.abi.datatypes.Utf8String(matriculaV), 
                new org.web3j.abi.datatypes.Utf8String(numeroP), 
                new org.web3j.abi.datatypes.Utf8String(estadoP), 
                new org.web3j.abi.datatypes.Utf8String(fechaV)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ContractCarChain_sol_simplestorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractCarChain_sol_simplestorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractCarChain_sol_simplestorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractCarChain_sol_simplestorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractCarChain_sol_simplestorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractCarChain_sol_simplestorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractCarChain_sol_simplestorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractCarChain_sol_simplestorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractCarChain_sol_simplestorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String nombreC, String DocumentoC, String matriculaV, String numeroP, String estadoP, String fechaV) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nombreC), 
                new org.web3j.abi.datatypes.Utf8String(DocumentoC), 
                new org.web3j.abi.datatypes.Utf8String(matriculaV), 
                new org.web3j.abi.datatypes.Utf8String(numeroP), 
                new org.web3j.abi.datatypes.Utf8String(estadoP), 
                new org.web3j.abi.datatypes.Utf8String(fechaV)));
        return deployRemoteCall(ContractCarChain_sol_simplestorage.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ContractCarChain_sol_simplestorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String nombreC, String DocumentoC, String matriculaV, String numeroP, String estadoP, String fechaV) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nombreC), 
                new org.web3j.abi.datatypes.Utf8String(DocumentoC), 
                new org.web3j.abi.datatypes.Utf8String(matriculaV), 
                new org.web3j.abi.datatypes.Utf8String(numeroP), 
                new org.web3j.abi.datatypes.Utf8String(estadoP), 
                new org.web3j.abi.datatypes.Utf8String(fechaV)));
        return deployRemoteCall(ContractCarChain_sol_simplestorage.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractCarChain_sol_simplestorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String nombreC, String DocumentoC, String matriculaV, String numeroP, String estadoP, String fechaV) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nombreC), 
                new org.web3j.abi.datatypes.Utf8String(DocumentoC), 
                new org.web3j.abi.datatypes.Utf8String(matriculaV), 
                new org.web3j.abi.datatypes.Utf8String(numeroP), 
                new org.web3j.abi.datatypes.Utf8String(estadoP), 
                new org.web3j.abi.datatypes.Utf8String(fechaV)));
        return deployRemoteCall(ContractCarChain_sol_simplestorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractCarChain_sol_simplestorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String nombreC, String DocumentoC, String matriculaV, String numeroP, String estadoP, String fechaV) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nombreC), 
                new org.web3j.abi.datatypes.Utf8String(DocumentoC), 
                new org.web3j.abi.datatypes.Utf8String(matriculaV), 
                new org.web3j.abi.datatypes.Utf8String(numeroP), 
                new org.web3j.abi.datatypes.Utf8String(estadoP), 
                new org.web3j.abi.datatypes.Utf8String(fechaV)));
        return deployRemoteCall(ContractCarChain_sol_simplestorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DataStoredEventResponse extends BaseEventResponse {
        public String nombreCliente;

        public String DocumentoCliente;

        public String matriculaVehiculo;

        public String numeroPoliza;

        public String estadoPoliza;

        public String fechaVencimiento;
    }
}
