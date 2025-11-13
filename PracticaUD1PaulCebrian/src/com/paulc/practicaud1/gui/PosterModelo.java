package com.paulc.practicaud1.gui;

import com.paulc.practicaud1.base.MusicaPoster;
import com.paulc.practicaud1.base.ObraPoster;
import com.paulc.practicaud1.base.PeliculaPoster;
import com.paulc.practicaud1.base.Poster;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;

public class PosterModelo {
    private ArrayList<Poster> listaPosters;

    public PosterModelo() {
        listaPosters = new ArrayList<Poster>();
    }

    public ArrayList<Poster> getListaPosters() {
        return listaPosters;
    }

    public void altaMusicaPoster(String titulo , String autor , String dimensiones , LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico, byte[] imagen , String estilo, String grupo, String nacionalidad) {
        MusicaPoster posterMusicaNuevo = new MusicaPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,estilo,grupo,nacionalidad);
        listaPosters.add(posterMusicaNuevo);
    }

    public void altaObraPoster(String titulo ,String autor ,  String dimensiones ,LocalDate fechaCreado,String lenguaje,int nCopias,boolean publico,byte[] imagen, int edicion, String tipoArte, String paletaColores) {
        ObraPoster posterObraNuevo = new ObraPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,edicion,tipoArte,paletaColores);
        listaPosters.add(posterObraNuevo);
    }

    public void altaPeliculaPoster(String titulo ,String autor ,  String dimensiones ,LocalDate fechaCreado,String lenguaje,int nCopias,boolean publico,byte[] imagen, String genero, String director ,float puntuacion) {
        PeliculaPoster posterPeliculaNuevo = new PeliculaPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,genero,director,puntuacion);
        listaPosters.add(posterPeliculaNuevo);
    }

    public boolean existeTitulo(String titulo) {
        for (Poster unPoster : listaPosters) {
            if (unPoster.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }
    public byte[] stringToBase64(String base64) {
         return  Base64.getDecoder().decode(base64);
    }

    public String base64ToString(byte[] imagen){

        return Base64.getEncoder().encodeToString(imagen);
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //añado el nodo raiz (la etiqueta que contiene las demas)
        Element raiz = documento.createElement("Posters");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoPoster = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Poster unPoster : listaPosters) {
            //añado dentro de Vehiculos los coches y motos
            if (unPoster instanceof MusicaPoster) {
                nodoPoster = documento.createElement("MusicaPoster");
            } else if (unPoster instanceof ObraPoster) {
                nodoPoster = documento.createElement("ObraPoster");
            } else {
                nodoPoster = documento.createElement("PeliculaPoster");
            }
            raiz.appendChild(nodoPoster);

            //dentro del vehiculo que corresponda añado etiquetas (matricula,marca,modelo,fechaMatriculacion)

            nodoDatos = documento.createElement("titulo");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(unPoster.getTitulo());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("autor");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(unPoster.getAutor());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("dimensiones");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(unPoster.getDimensiones());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha-creado");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(unPoster.getFechaCreado().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("lenguaje");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(unPoster.getLenguaje());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("numero-copias");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(unPoster.getnCopias()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("publico");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(String.valueOf(unPoster.isPublico()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("imagen");
            nodoPoster.appendChild(nodoDatos);

            texto = documento.createTextNode(base64ToString(unPoster.getImagen()));
            nodoDatos.appendChild(texto);

            if (unPoster instanceof MusicaPoster) {
                nodoDatos = documento.createElement("estilo");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((MusicaPoster) unPoster).getEstilo());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("grupo");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((MusicaPoster) unPoster).getGrupo());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("nacionalidad");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((MusicaPoster) unPoster).getNacionalidad());
                nodoDatos.appendChild(texto);
            } else if (unPoster instanceof ObraPoster) {
                nodoDatos = documento.createElement("edicion");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode((String.valueOf(((ObraPoster) unPoster).getEdicion())));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tipoArte");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode((((ObraPoster) unPoster).getTipoArte()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("paletaColores");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((ObraPoster) unPoster).getPaletaColores());
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = documento.createElement("genero");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((PeliculaPoster) unPoster).getGenero());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("director");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((PeliculaPoster) unPoster).getDirector());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("nacionalidad");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(((PeliculaPoster) unPoster).getPuntuacion()));
                nodoDatos.appendChild(texto);
            }
        }

        //guardo datos en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaPosters = new ArrayList<Poster>();
        MusicaPoster nuevaMusicaPoster = null;
        ObraPoster nuevaObraPoster = null;
        PeliculaPoster nuevaPeliculaPoster = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoPoster = (Element) listaElementos.item(i);

            if (nodoPoster.getTagName().equals("MusicaPoster")) {
                nuevaMusicaPoster = new MusicaPoster();
                nuevaMusicaPoster.setTitulo(nodoPoster.getChildNodes().item(0).getTextContent());
                nuevaMusicaPoster.setAutor(nodoPoster.getChildNodes().item(1).getTextContent());
                nuevaMusicaPoster.setDimensiones(nodoPoster.getChildNodes().item(2).getTextContent());
                nuevaMusicaPoster.setFechaCreado(LocalDate.parse(nodoPoster.getChildNodes().item(3).getTextContent()));
                nuevaMusicaPoster.setLenguaje(nodoPoster.getChildNodes().item(4).getTextContent());
                nuevaMusicaPoster.setnCopias(Integer.parseInt(nodoPoster.getChildNodes().item(5).getTextContent()));
                nuevaMusicaPoster.setPublico(Boolean.parseBoolean(nodoPoster.getChildNodes().item(6).getTextContent()));
                nuevaMusicaPoster.setImagen(stringToBase64(nodoPoster.getChildNodes().item(7).getTextContent()));
                nuevaMusicaPoster.setEstilo(nodoPoster.getChildNodes().item(8).getTextContent());
                nuevaMusicaPoster.setGrupo(nodoPoster.getChildNodes().item(9).getTextContent());
                nuevaMusicaPoster.setNacionalidad(nodoPoster.getChildNodes().item(10).getTextContent());

                listaPosters.add(nuevaMusicaPoster);
            } else if (nodoPoster.getTagName().equals("ObraPoster")) {
                nuevaObraPoster = new ObraPoster();
                nuevaObraPoster.setTitulo(nodoPoster.getChildNodes().item(0).getTextContent());
                nuevaObraPoster.setAutor(nodoPoster.getChildNodes().item(1).getTextContent());
                nuevaObraPoster.setDimensiones(nodoPoster.getChildNodes().item(2).getTextContent());
                nuevaObraPoster.setFechaCreado(LocalDate.parse(nodoPoster.getChildNodes().item(3).getTextContent()));
                nuevaObraPoster.setLenguaje(nodoPoster.getChildNodes().item(4).getTextContent());
                nuevaObraPoster.setnCopias(Integer.parseInt(nodoPoster.getChildNodes().item(5).getTextContent()));
                nuevaObraPoster.setPublico(Boolean.parseBoolean(nodoPoster.getChildNodes().item(6).getTextContent()));
                nuevaObraPoster.setImagen(stringToBase64(nodoPoster.getChildNodes().item(7).getTextContent()));
                nuevaObraPoster.setEdicion(Integer.parseInt(nodoPoster.getChildNodes().item(8).getTextContent()));
                nuevaObraPoster.setTipoArte(nodoPoster.getChildNodes().item(9).getTextContent());
                nuevaObraPoster.setPaletaColores(nodoPoster.getChildNodes().item(10).getTextContent());


                listaPosters.add(nuevaMusicaPoster);
            } else if (nodoPoster.getTagName().equals("PeliculaPoster")) {
                nuevaPeliculaPoster = new PeliculaPoster();
                nuevaPeliculaPoster.setTitulo(nodoPoster.getChildNodes().item(0).getTextContent());
                nuevaPeliculaPoster.setAutor(nodoPoster.getChildNodes().item(1).getTextContent());
                nuevaPeliculaPoster.setDimensiones(nodoPoster.getChildNodes().item(2).getTextContent());
                nuevaPeliculaPoster.setFechaCreado(LocalDate.parse(nodoPoster.getChildNodes().item(3).getTextContent()));
                nuevaPeliculaPoster.setLenguaje(nodoPoster.getChildNodes().item(4).getTextContent());
                nuevaPeliculaPoster.setnCopias(Integer.parseInt(nodoPoster.getChildNodes().item(5).getTextContent()));
                nuevaPeliculaPoster.setPublico(Boolean.parseBoolean(nodoPoster.getChildNodes().item(6).getTextContent()));
                nuevaPeliculaPoster.setImagen(stringToBase64(nodoPoster.getChildNodes().item(7).getTextContent()));
                nuevaPeliculaPoster.setGenero(nodoPoster.getChildNodes().item(8).getTextContent());
                nuevaPeliculaPoster.setDirector(nodoPoster.getChildNodes().item(9).getTextContent());
                nuevaPeliculaPoster.setPuntuacion(Float.parseFloat(nodoPoster.getChildNodes().item(10).getTextContent()));

                listaPosters.add(nuevaMusicaPoster);
            }
        }

    }


    public void ordenar(){
        this.listaPosters.sort(Comparator.comparing(Poster::getTitulo));
    }
}
