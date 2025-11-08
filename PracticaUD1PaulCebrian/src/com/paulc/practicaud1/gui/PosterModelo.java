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

public class PosterModelo {
    private ArrayList<Poster> listaPosters;

    public PosterModelo() {
        listaPosters = new ArrayList<Poster>();
    }

    public ArrayList<Poster> obtenerVehiculos() {
        return listaPosters;
    }

    public void altaMusicaPoster() {
        MusicaPoster posterMusicaNuevo = new MusicaPoster();
        listaPosters.add(posterMusicaNuevo);
    }

    public void altaObraPoster() {
        ObraPoster posterObraNuevo = new ObraPoster();
        listaPosters.add(posterObraNuevo);
    }
    public void altaPeliculaPoster() {
        PeliculaPoster posterPeliculaNuevo = new PeliculaPoster();
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

            texto = documento.createTextNode(unPoster.getImagen());
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
            } else if(unPoster instanceof  ObraPoster){
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
            } else{
                nodoDatos = documento.createElement("genero");
                nodoPoster.appendChild(nodoDatos);

                texto = documento.createTextNode(((PeliculaPoster)unPoster).getGenero());
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
        listaVehiculos = new ArrayList<Vehiculo>();
        Coche nuevoCoche = null;
        Moto nuevoMoto = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoVehiculo = (Element) listaElementos.item(i);

            if (nodoVehiculo.getTagName().equals("Coche")) {
                nuevoCoche = new Coche();
                nuevoCoche.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevoCoche.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevoCoche.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevoCoche.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevoCoche.setNumPlazas(Integer.parseInt(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevoCoche);
            } else {
                if (nodoVehiculo.getTagName().equals("Moto")) {
                    nuevoMoto = new Moto();
                    nuevoMoto.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                    nuevoMoto.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                    nuevoMoto.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                    nuevoMoto.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                    nuevoMoto.setKms(Double.parseDouble(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                    listaVehiculos.add(nuevoMoto);
                }
            }
        }

    }
