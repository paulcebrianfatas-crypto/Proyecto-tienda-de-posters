package com.paulc.practicaud1.gui;

import com.paulc.practicaud1.base.MusicaPoster;
import com.paulc.practicaud1.base.ObraPoster;
import com.paulc.practicaud1.base.PeliculaPoster;
import com.paulc.practicaud1.base.Poster;
import com.paulc.practicaud1.util.Util;
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
import java.util.Iterator;

/**
 * Clase que gestiona una colección de objetos Poster.
 * Permite dar de alta distintos tipos de poster, exportarlos e importarlos en XML,
 * ordenar, limpiar y verificar su existencia.
 */
public class PosterModelo {

    /** Lista principal donde se almacenan los posters */
    private ArrayList<Poster> listaPosters;

    /** Constructor que inicializa la lista */
    public PosterModelo() {
        listaPosters = new ArrayList<Poster>();
    }

    /**
     * Devuelve la lista completa de posters
     */
    public ArrayList<Poster> getListaPosters() {
        return listaPosters;
    }

    /**
     * Da de alta un nuevo MusicaPoster con sus atributos
     */
    public void altaMusicaPoster(String titulo , String autor , String dimensiones , LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico, byte[] imagen , String estilo, String grupo, String nacionalidad) {
        MusicaPoster posterMusicaNuevo = new MusicaPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,estilo,grupo,nacionalidad);
        listaPosters.add(posterMusicaNuevo);
    }

    /**
     * Da de alta un nuevo ObraPoster con sus atributos
     */
    public void altaObraPoster(String titulo ,String autor ,  String dimensiones ,LocalDate fechaCreado,String lenguaje,int nCopias,boolean publico,byte[] imagen, int edicion, String tipoArte, String paletaColores) {
        ObraPoster posterObraNuevo = new ObraPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,edicion,tipoArte,paletaColores);
        listaPosters.add(posterObraNuevo);
    }

    /**
     * Da de alta un nuevo PeliculaPoster con sus atributos
     */
    public void altaPeliculaPoster(String titulo ,String autor ,  String dimensiones ,LocalDate fechaCreado,String lenguaje,int nCopias,boolean publico,byte[] imagen, String genero, String director ,float puntuacion) {
        PeliculaPoster posterPeliculaNuevo = new PeliculaPoster(titulo,autor,dimensiones,fechaCreado,lenguaje,nCopias,publico,imagen,genero,director,puntuacion);
        listaPosters.add(posterPeliculaNuevo);
    }

    /**
     * Verifica si existe un poster con un título concreto
     */
    public boolean existeTitulo(String titulo) {
        for (Poster unPoster : listaPosters) {
            if (unPoster.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convierte un String Base64 en un array de bytes
     */
    public byte[] stringToBase64(String base64) {
        return  Base64.getDecoder().decode(base64);
    }

    /**
     * Convierte una imagen (byte[]) a un String codificado en Base64
     */
    public String base64ToString(byte[] imagen){
        return Base64.getEncoder().encodeToString(imagen);
    }

    /**
     * Exporta el contenido de la lista de posters a un archivo XML
     */
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        // Nodo raíz del documento XML
        Element raiz = documento.createElement("Posters");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoPoster = null;
        Element nodoDatos = null;
        Text texto = null;

        // Recorrer todos los posters y añadirlos al XML
        for (Poster unPoster : listaPosters) {

            // Crear etiqueta según el tipo de poster
            if (unPoster instanceof MusicaPoster) {
                nodoPoster = documento.createElement("MusicaPoster");
            } else if (unPoster instanceof ObraPoster) {
                nodoPoster = documento.createElement("ObraPoster");
            } else {
                nodoPoster = documento.createElement("PeliculaPoster");
            }
            raiz.appendChild(nodoPoster);

            // Añadir atributos comunes
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

            // Añadir atributos específicos según el tipo de poster
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

        // Guardar el XML en el archivo indicado
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    /**
     * Importa datos desde un archivo XML y genera los objetos Poster correspondientes
     */
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaPosters = new ArrayList<Poster>();
        MusicaPoster nuevaMusicaPoster = null;
        ObraPoster nuevaObraPoster = null;
        PeliculaPoster nuevaPeliculaPoster = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        // Recorrer nodos del XML y reconstruir objetos
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

                listaPosters.add(nuevaObraPoster);

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

                listaPosters.add(nuevaPeliculaPoster);
            }
        }
    }

    /** Vacía la lista de posters */
    public void limpiar(){
        listaPosters.clear();
    }

    /** Ordena los posters alfabéticamente por su título */
    public void ordenar(){
        this.listaPosters.sort(Comparator.comparing(Poster::getTitulo));
    }

    /**
     * Actualiza un poster eliminándolo por título (lógica de reemplazo externa)
     */
    public boolean actualizar(Poster poster) {
        Iterator<Poster> iterador = listaPosters.iterator();
        while (iterador.hasNext()) {
            Poster unPoster = iterador.next();
            if (unPoster.getTitulo().equals(poster.getTitulo())){
                iterador.remove();
                return true;
            }
        }
        return false;
    }

    /** Muestra los datos usando la utilidad externa */
    public void mostrarDatos() {
        Util.mostrarDatos(getListaPosters());
    }
}
