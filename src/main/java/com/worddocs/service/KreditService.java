package com.worddocs.service;

import com.worddocs.model.Kredit;
import com.worddocs.model.AzolikFiz;
import com.worddocs.repository.KreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KreditService {

    @Autowired
    private KreditRepository kreditRepository;

    // Метод для поиска кредита по коду
    public Optional<Kredit> findKreditByKod(String kod) {
        return kreditRepository.findById(kod);
    }

    // Метод для получения физического лица по коду кредита
    public Optional<AzolikFiz> findAzolikByKreditKod(String kod) {
        Optional<Kredit> kreditOpt = kreditRepository.findById(kod);
        if (kreditOpt.isPresent() && kreditOpt.get().getAzolikFiz() != null) {
            return Optional.of(kreditOpt.get().getAzolikFiz());
        }
        return Optional.empty();
    }
}
