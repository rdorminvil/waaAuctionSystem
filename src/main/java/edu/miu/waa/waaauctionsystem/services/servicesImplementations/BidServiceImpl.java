package edu.miu.waa.waaauctionsystem.services.servicesImplementations;


import edu.miu.waa.waaauctionsystem.models.Bid;
import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.repositories.BidRepository;
import edu.miu.waa.waaauctionsystem.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    @Override
    public List<Bid> getAll() {
        return bidRepository.findAll();
    }

    @Override
    public Optional<Bid> getById(Long id) {
        return bidRepository.findById(id);
    }

    @Override
    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public void deleteBidById(Long id) {
        bidRepository.deleteById(id);
    }
}
