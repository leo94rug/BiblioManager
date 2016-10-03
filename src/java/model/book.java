/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leo
 */
public class Book {
    private String isbn;
    private String titolo;
    private String autore;
    private String editore;
    private String descrizione;
    private String url_img;
    private String utente_fk;
    private String anno_pub;
    private String lingua;
    private String ultima_mod;
    private String buy;
    private String link_buy;
    private String link_download;
    private String size;
    private String type;
    private String data_ins;

    public void setData_ins(String data_ins) {
        this.data_ins = data_ins;
    }

    public String getData_ins() {
        return data_ins;
    }
    

    public Book(String isbn, String titolo, String autore, String editore, String descrizione, String url_img, String utente_fk, String anno_pub, String lingua, String ultima_mod, String buy, String link_buy, String link_download, String size, String type, String data_ins) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.editore = editore;
        this.descrizione = descrizione;
        this.url_img = url_img;
        this.utente_fk = utente_fk;
        this.anno_pub = anno_pub;
        this.lingua = lingua;
        this.ultima_mod = ultima_mod;
        this.buy = buy;
        this.link_buy = link_buy;
        this.link_download = link_download;
        this.size = size;
        this.type = type;
        this.data_ins = data_ins;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setUtente_fk(String utente_fk) {
        this.utente_fk = utente_fk;
    }

    public void setAnno_pub(String anno_pub) {
        this.anno_pub = anno_pub;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public void setUltima_mod(String ultima_mod) {
        this.ultima_mod = ultima_mod;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setLink_buy(String link_buy) {
        this.link_buy = link_buy;
    }

    public void setLink_download(String link_download) {
        this.link_download = link_download;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getEditore() {
        return editore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getUrl_img() {
        return url_img;
    }

    public String getUtente_fk() {
        return utente_fk;
    }

    public String getAnno_pub() {
        return anno_pub;
    }

    public String getLingua() {
        return lingua;
    }

    public String getUltima_mod() {
        return ultima_mod;
    }

    public String getBuy() {
        return buy;
    }

    public String getLink_buy() {
        return link_buy;
    }

    public String getLink_download() {
        return link_download;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
