package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.Bid;
import edu.miu.waa.waaauctionsystem.models.Product;

import java.util.List;
import java.util.Optional;

public interface BidService {
    public List<Bid> getAll();
    public Optional<Bid> getById(Long id);
    public Bid createBid(Bid  bid);
    public void deleteBidById(Long id);
    }
