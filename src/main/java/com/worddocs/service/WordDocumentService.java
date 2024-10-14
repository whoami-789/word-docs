package com.worddocs.service;

import com.worddocs.model.AzolikFiz;
import com.worddocs.model.Grafik;
import com.worddocs.model.Kredit;
import com.worddocs.repository.AzolikFizRepository;
import com.worddocs.repository.GrafikRepository;
import com.worddocs.repository.KreditRepository;

import com.worddocs.repository.SaldoRepository;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class WordDocumentService {

    private final KreditRepository kreditRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final GrafikRepository grafikRepository;
    private final SaldoRepository saldoRepository;

    public WordDocumentService(KreditRepository kreditRepository, AzolikFizRepository azolikFizRepository, GrafikRepository grafikRepository, SaldoRepository saldoRepository) {
        this.kreditRepository = kreditRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.grafikRepository = grafikRepository;
        this.saldoRepository = saldoRepository;
    }

    // Метод для генерации документа

    public byte[] generateWarning(String kreditKod) throws IOException {
        Kredit kredit = kreditRepository.findByNumdog(kreditKod)
                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));

        BigDecimal proc = saldoRepository.findSumsByLs(kredit.getLsproc());

        AzolikFiz azolikFiz = kredit.getAzolikFiz();

        // Загрузка шаблона
        try (InputStream templateStream = new ClassPathResource("templates/LOMBARD_OGOHLANTIRISH.doc").getInputStream();
             HWPFDocument document = new HWPFDocument(templateStream)) {

            Range range = document.getRange();

            // Заменяем плейсхолдеры
            replacePlaceholder(range, "{{address}}", azolikFiz.getAdres());
            replacePlaceholder(range, "{{name}}", azolikFiz.getName());
            replacePlaceholder(range, "{{kod}}", kredit.getNumdog());
            replacePlaceholder(range, "{{summa}}", String.valueOf(kredit.getSumma()));
            replacePlaceholder(range, "{{summa2}}", String.valueOf(proc.doubleValue()));
            replacePlaceholder(range, "{{mes}}", String.valueOf(kredit.getSrokkred()));

            // Сохранение сгенерированного документа в байты
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                document.write(out);
                return out.toByteArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обработке документа: " + e.getMessage(), e);
        }
    }

    public byte[] generateGrafik(String kreditKod) throws IOException {
        // Получаем данные из базы
        Kredit kredit = kreditRepository.findById(kreditKod)
                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));

        AzolikFiz azolikFiz = kredit.getAzolikFiz();

        // Загрузка шаблона
        InputStream templateStream = new ClassPathResource("templates/grafik.doc").getInputStream();
        HWPFDocument document = new HWPFDocument(templateStream);
        Range range = document.getRange();

        // Заменяем плейсхолдеры
        replacePlaceholder(range, "${fio}", azolikFiz.getName());
        replacePlaceholder(range, "${kreditSumma}", String.valueOf(kredit.getSumma()));
        replacePlaceholder(range, "${muddat}", String.valueOf(kredit.getSrokkred()));
        replacePlaceholder(range, "${oylikFoiz}", String.valueOf(kredit.getProsent()));

        // Заполнение таблицы
        TableIterator tableIterator = new TableIterator(range);
        if (tableIterator.hasNext()) {
            Table table = tableIterator.next();

            // Создаем новую таблицу
            List<Grafik> grafikList = grafikRepository.findAllByNumdog(kredit.getNumdog());

            // Вставляем данные в таблицу
            for (Grafik grafik : grafikList) {
                TableRow newRow = new TableRow(0, 0, table, table.getTableLevel());
                newRow.getCell(0).insertBefore(String.valueOf(grafikList.indexOf(grafik) + 1)); // Номер
                newRow.getCell(1).insertBefore(String.valueOf(grafik.getDats())); // Дата
                newRow.getCell(2).insertBefore(String.valueOf(grafik.getPogKred())); // Погашение кредита
                newRow.getCell(3).insertBefore(String.valueOf(grafik.getPogProc())); // Погашение процентов
                newRow.getCell(4).insertBefore(String.valueOf(grafik.getOstatok())); // Оставшаяся сумма
                newRow.getCell(5).insertBefore(String.valueOf(grafik.getPogKred().intValue() + grafik.getPogProc().intValue())); // Сумма

                // Здесь вам нужно будет реализовать логику, чтобы вставить newRow в нужное место в документе
            }

        }

        // Сохранение сгенерированного документа в байты
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);

        return out.toByteArray();
    }


    private void replacePlaceholder(Range range, String placeholder, String value) {
        for (int i = 0; i < range.numParagraphs(); i++) {
            Paragraph paragraph = range.getParagraph(i);
            for (int j = 0; j < paragraph.numCharacterRuns(); j++) {
                CharacterRun run = paragraph.getCharacterRun(j);
                String text = run.text();
                if (text.contains(placeholder)) {
                    // Заменяем текст и сохраняем форматирование
                    text = text.replace(placeholder, value);
                    run.delete(); // Удаляем текущий run
                    run = paragraph.insertBefore(text); // Вставляем новый текст
                }
            }
        }
    }





}

