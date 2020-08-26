package com.a3infortech.importfiles.infra;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Component
public class DatasHoras {

    public static final String ZONE_ID = "America/Recife";
    private DateTimeFormatter formatter;

    public Boolean comparaDataSeAnterior(LocalDate dataRegistro) {
        return dataRegistro.isBefore(LocalDate.now(ZoneId.of(ZONE_ID)));
    }

    public boolean comparaDatasIguais(LocalDate data, LocalDate data1) {
        return data.isEqual(data1);
    }

    public LocalDate getDataAtual() {
        return LocalDate.now(ZoneId.of(ZONE_ID));
    }

    public LocalTime getHoraAtual() {
        return LocalTime.now(ZoneId.of(ZONE_ID));
    }

    public LocalDateTime getDataHoraAtual() {
        return LocalDateTime.now(ZoneId.of(ZONE_ID));
    }

    public LocalDate somaDias(LocalDate dataRecebida, Long dias) {
        return dataRecebida.plusDays(dias);
    }

    public LocalDate somaMes(LocalDate date, Long months){
        return date.plusMonths(months);
    }

    public String dateTimeToTimeString(LocalDateTime dataHora) {
        StringBuilder builder = new StringBuilder();
        builder.append(acrescentaDigitos(dataHora.getHour(), 2));
        builder.append(":");
        builder.append(acrescentaDigitos(dataHora.getMinute(), 2));
        builder.append(":");
        builder.append(acrescentaDigitos(dataHora.getSecond(), 2));
        return builder.toString();
    }

    public LocalDate retiraLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public String acrescentaDigitos(Integer entrada, Integer digitos) {
        StringBuilder retorno = new StringBuilder();
        IntStream.range(entrada.toString().length(), digitos)
                .forEach(it -> {
                    retorno.append("0");
                });
        retorno.append(entrada.toString());
        return retorno.toString();
    }

    public String tempoEntreDateTime(LocalDateTime inicio, LocalDateTime fim) {
        Duration duracao = Duration.between(inicio, fim);
        return getDurationBreakdown(duracao.toMillis());
    }

    public long tempoEntreDateTimeEmMinutos(LocalDateTime inicio, LocalDateTime fim) {
        Duration duracao = Duration.between(inicio, fim);
        return TimeUnit.MILLISECONDS.toMinutes(duracao.toMillis());
    }

    public String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        /*sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");*/
        sb.append(acrescentaDigitos((int) hours, 2));
        sb.append(":");
        sb.append(acrescentaDigitos((int) minutes, 2));
        sb.append(":");
        sb.append(acrescentaDigitos((int) seconds, 2));

        return (sb.toString());
    }

    public String dataString(LocalDate localDate) {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    public String horaString(LocalTime localTime) {
        this.formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }

    public String getFusoHorario() {
        return ZONE_ID;
    }

    public LocalTime stringToLocalTime(String time) {
        return LocalTime.parse(time);
    }

    public String formataHora(LocalTime localTime) {
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localTime.format(this.formatter);
    }

    public LocalDate stringToLocalDate(String data) {
        return LocalDate.parse(data);
    }

    public LocalDate dateToLocalDate(Date data){
        return data.toInstant().atZone(ZoneId.of(ZONE_ID)).toLocalDate();
    }

    public LocalDateTime stringDateTimeToLocalDateTime(String dateTime){
        return LocalDateTime.parse(dateTime);
    }

    public LocalDateTime dateTimeToLocalDateDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(ZONE_ID));
    }

    public LocalDateTime localDateToLocalDateTimeStartOfADay(LocalDate localdate) {
        return localdate.atTime(0,0);
    }

    public LocalDateTime localDateToLocalDateTimeEndOfADay(LocalDate localdate) {
        return localdate.atTime(23,59);
    }
}
