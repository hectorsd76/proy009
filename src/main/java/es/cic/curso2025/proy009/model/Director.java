package es.cic.curso2025.proy009.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Director")
public class Director {

    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    
    @Column(name= "edad")
    private int edad;

    @Column(name = "numero_peliculas")
    private long numeroPeliculas;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @JsonManagedReference
    @OneToMany(cascade ={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Pelicula> peliculas = new ArrayList<>();


    // Constructores
    public Director(){

    }

    public Director(String nombre, int edad, long numeroPeliculas,String nacionalidad){
        this.nombre = nombre;
        this.edad = edad;
        this.numeroPeliculas = numeroPeliculas;
        this.nacionalidad = nacionalidad;
    }

 
    public Director(Long id,String nombre, int edad, long numeroPeliculas,String nacionalidad){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.numeroPeliculas = numeroPeliculas;
        this.nacionalidad = nacionalidad;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getNumeroPeliculas() {
        return numeroPeliculas;
    }

    public void setNumeroPeliculas(long numeroPeliculas) {
        this.numeroPeliculas = numeroPeliculas;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    // HASH
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + edad;
        result = prime * result + (int) (numeroPeliculas ^ (numeroPeliculas >>> 32));
        result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
        result = prime * result + ((peliculas == null) ? 0 : peliculas.hashCode());
        return result;
    }
    
    // EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Director other = (Director) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (edad != other.edad)
            return false;
        if (numeroPeliculas != other.numeroPeliculas)
            return false;
        if (nacionalidad == null) {
            if (other.nacionalidad != null)
                return false;
        } else if (!nacionalidad.equals(other.nacionalidad))
            return false;
        if (peliculas == null) {
            if (other.peliculas != null)
                return false;
        } else if (!peliculas.equals(other.peliculas))
            return false;
        return true;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Director [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", numeroPeliculas=" + numeroPeliculas
                + ", nacionalidad=" + nacionalidad + ", peliculas=" + peliculas + "]";
    }


   
    



    

    
}
