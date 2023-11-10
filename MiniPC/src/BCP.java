
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 
 Estados; nuevo, preparado, ejecución, en espera, finalizado
Contador del programa (ubicación del programa cargado en memoria)
Registros AC, AX, BX, CX, DX
Información de la pila: definir tamaño de 5, y tomar en cuenta error de desbordamiento
Información contable; el cpu donde se está ejecutando, tiempo de inicio, tiempo empleado.
Información del estado de E/S; lista de archivos abiertos
Enlace al siguiente BPC
dirección inicio (Base)
Tamaño del proceso (Alcance)
Prioridad 

 */

//cada bcp usa 12 lineas
public class BCP {
    private String identificador;
    private  String estado;//nuevo, preparado, ejecución, en espera, finalizado
    private int PC;//ubicación del programa cargado en memoria
    private Stack<Integer> pila = new Stack<Integer>();
    

    private int cpuActual;
    private Date tiempoInicio;
    private Date tiempoFin;
    private String tiempoEmpleado = "Por definir";
    private String estadoInterrupcion;
    private int siguienteBPC;//primera linea del siguiente BPC
    private int base;//linea inicio
    private int alcance; //cantidad de lineas de codigo
    private String prioridad;
    private Boolean comp = null;
    
    private Dictionary<String, Registro> registros = new Hashtable<>();

   
    
   
    public BCP(String identificador) {
        
        System.out.println("Cree bcp: "+ identificador );
        this.identificador = identificador;
        this.estado = "nuevo";
        this.estadoInterrupcion = "En espera";
        this.prioridad = "Normal";
        this.cpuActual = 1;
        this.registros.put("AX", new Registro("AX")); //0001
        this.registros.put("BX", new Registro("BX"));//0010
        this.registros.put("CX", new Registro("CX"));//0011
        this.registros.put("DX", new Registro("DX"));//0100
        this.registros.put("AC", new Registro("AC"));
        this.registros.put("PC", new Registro("PC"));
        this.registros.put("IR", new Registro("IR"));
        
        this.pila.push(null);
        this.pila.push(null);
        this.pila.push(null);
        this.pila.push(null);
        this.pila.push(null);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public Stack<Integer> getPila() {
        return pila;
    }

    public void setPila(Stack<Integer> pila) {
        this.pila = pila;
    }

    public int getCpuActual() {
        return cpuActual;
    }

    public void setCpuActual(int cpuActual) {
        this.cpuActual = cpuActual;
    }

    public Date getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(Date tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public Date getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(Date tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    
    
    public String getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(String tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public String getEstadoInterrupcion() {
        return estadoInterrupcion;
    }

    public void setEstadoInterrupcion(String estadoInterrupcion) {
        this.estadoInterrupcion = estadoInterrupcion;
    }

    public int getSiguienteBPC() {
        return siguienteBPC;
    }

    public void setSiguienteBPC(int siguienteBPC) {
        this.siguienteBPC = siguienteBPC;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Dictionary<String, Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(Dictionary<String, Registro> registros) {
        registros = registros;
    }

    public Boolean getComp() {
        return comp;
    }

    public void setComp(Boolean comp) {
        this.comp = comp;
    }
    
   public void agregarAPila(int valor){
       int cantNull = 0;
       for(Integer item : pila){
           if (item == null) cantNull +=1;
           
       }
       
       if(cantNull >0){
           pila.push(valor);
           pila.remove(0);
           
       }else{
           cargarArchivo.mostrarError(0, "Espacio en pila insuficiente");
       }
   }
   
   public Integer eliminarDePila(){
       int cantNull = 0;
       for(Integer item : pila){
           if (item == null) cantNull +=1;
           
       }

        Integer valor = null;
       if(pila.size()>0){
           valor = pila.pop();
           pila.add(0,null);
       }else{
           cargarArchivo.mostrarError(0, "Indice fuera de rango para tamaño "+ pila.size());
       }
       return valor;
   }
    
}
