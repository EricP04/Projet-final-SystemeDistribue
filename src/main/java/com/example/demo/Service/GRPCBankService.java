package com.example.demo.Service;

import bankgrpc.gen.proto.AskMoneyRequest;
import bankgrpc.gen.proto.BankContactGrpc;
import bankgrpc.gen.proto.BankGRPC;
import bankgrpc.gen.proto.ReplyBank;
import org.springframework.stereotype.Service;
import io.grpc.*;
@Service
public class GRPCBankService {

    public boolean askMoney(String numAccount, float amountAsk)
    {
        ManagedChannel managedChannel =
                ManagedChannelBuilder.forAddress("localhost",60100)
                .usePlaintext()
                .build();
        BankContactGrpc.BankContactBlockingStub stub = BankContactGrpc.newBlockingStub(managedChannel);
        ReplyBank reply = stub.askMoney(AskMoneyRequest.newBuilder().setAskAmount(amountAsk).setNumAccount(numAccount).build());
        managedChannel.shutdown();
        return reply.getReply();
    }
}
