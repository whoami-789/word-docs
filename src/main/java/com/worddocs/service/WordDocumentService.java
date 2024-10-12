package com.worddocs.service;

import com.worddocs.model.AzolikFiz;
import com.worddocs.model.Grafik;
import com.worddocs.model.Kredit;
import com.worddocs.repository.AzolikFizRepository;
import com.worddocs.repository.GrafikRepository;
import com.worddocs.repository.KreditRepository;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class WordDocumentService {

    private final KreditRepository kreditRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final GrafikRepository grafikRepository;

    public WordDocumentService(KreditRepository kreditRepository, AzolikFizRepository azolikFizRepository, GrafikRepository grafikRepository) {
        this.kreditRepository = kreditRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.grafikRepository = grafikRepository;
    }

    // Метод для генерации документа

    public byte[] generateWarning(String kreditKod) throws IOException {
        Kredit kredit = kreditRepository.findByNumdog(kreditKod)
                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));

        AzolikFiz azolikFiz = kredit.getAzolikFiz();

        // Загрузка шаблона
        try (InputStream templateStream = new ClassPathResource("templates/LOMBARD_OGOHLANTIRISH.doc").getInputStream();
             HWPFDocument document = new HWPFDocument(templateStream)) {

            Range range = document.getRange();

            // Заменяем плейсхолдеры
            replacePlaceholder(range, "{{kod}}", kredit.getKod());
            replacePlaceholder(range, "{{summa}}", String.valueOf(kredit.getSumma()));
            replacePlaceholder(range, "{{summa2}}", String.valueOf(kredit.getSumma()));
            replacePlaceholder(range, "{{summa3}}", String.valueOf(kredit.getSumma()));

            // Сохранение сгенерированного документа в байты
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                document.write(out);
                return out.toByteArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обработке документа: " + e.getMessage(), e);
        }
    }

    private void replacePlaceholder(Range range, String placeholder, String value) {
        String text = range.text();
        // Заменяем все вхождения плейсхолдера
        text = text.replace(placeholder, value);
        range.replaceText(range.text(), text);
    }




//    public byte[] generateGrafik(String kreditKod) throws IOException {
//        // Получаем данные из базы
//        Kredit kredit = kreditRepository.findById(kreditKod)
//                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));
//
//        AzolikFiz azolikFiz = kredit.getAzolikFiz();
//
//        // Загрузка шаблона
//        InputStream templateStream = new ClassPathResource("templates/grafik.doc").getInputStream();
//        XWPFDocument document = new XWPFDocument(templateStream);
//
//        // Проходим по параграфам и заменяем плейсхолдеры
//        for (XWPFParagraph paragraph : document.getParagraphs()) {
//            String text = paragraph.getText();
//            paragraph.getRuns().clear(); // Очищаем текущие run
//
//            // Проверяем и заменяем плейсхолдеры
//            if (text.contains("${fio}")) {
//                text = text.replace("${fio}", azolikFiz.getName());
//            }
//            if (text.contains("${kreditSumma}")) {
//                text = text.replace("${kreditSumma}", String.valueOf(kredit.getSumma()));
//            }
//            if (text.contains("${muddat}")) {
//                text = text.replace("${muddat}", String.valueOf(kredit.getSrokkred()));
//            }
//            if (text.contains("${oylikFoiz}")) {
//                text = text.replace("${oylikFoiz}", String.valueOf(kredit.getProsent()));
//            }
//
//            // Добавляем обновленный текст
//            paragraph.createRun().setText(text);
//        }
//
//        // Заполнение таблицы
//        XWPFTable table = document.getTableArray(0); // Предполагаем, что таблица первая
//
//        // Добавление данных в таблицу
//        if (table != null) {
//            // Предположим, что у вас есть метод для получения всех Grafik, связанных с кредитом
//            List<Grafik> grafikList = grafikRepository.findAllByNumdog(kredit.getNumdog());
//
//            // Удаляем первую пустую строку, если она есть
//            while (table.getNumberOfRows() > 1) {
//                table.removeRow(1); // Удаляем строки, начиная со второй (первая - заголовок)
//            }
//            int rowIndex = 1; // Начинаем с 1, если строка заголовка - это 0
//
//            // Добавление данных в таблицу
//            for (Grafik grafik : grafikList) {
//                XWPFTableRow newRow = table.createRow();
//                // Установка номера в первую ячейку
//                newRow.getCell(0).setText(String.valueOf(rowIndex++)); // Увеличиваем номер после использования
//                // Установка остальных данных
//                newRow.getCell(1).setText(String.valueOf(grafik.getDats()));
//                newRow.getCell(2).setText(String.valueOf(grafik.getPogKred()));
//                newRow.getCell(3).setText(String.valueOf(grafik.getPogProc()));
//                newRow.getCell(4).setText(String.valueOf(grafik.getOstatok()));
//                newRow.getCell(5).setText(String.valueOf(grafik.getPogKred().intValue() + grafik.getPogProc().intValue()));
//                // Добавьте дополнительные столбцы по необходимости
//            }
//        }
//
//        // Сохранение сгенерированного документа в байты
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        document.write(out);
//
//        return out.toByteArray();
//    }




}

