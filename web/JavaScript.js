/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//document.getElementById("borrar").addEventListener('click', confirmar(), false);
function pararAccionPorDefecto(evt) {
    evt.preventDefault();
}

function confirmar()
{
    if(confirm('¿Estas seguro de que quieres borrar esta oferta?'))
        document.forms.curriculums.submit();
    else    
        return false;
        
}

//document.getElementsByTagName("body")[0].addEventListener('onload', muestraReloj(), false)
//window.onload=muestraReloj()
function muestraReloj()
{
    // Comprueba si se puede ejecutar el script en el navegador del usuario
    if (!document.layers && !document.all && !document.getElementById) return;
    // Obtengo la hora actual y la divido en sus partes
    var fechacompleta = new Date();
    var horas = fechacompleta.getHours();
    var minutos = fechacompleta.getMinutes();
    var segundos = fechacompleta.getSeconds();
    var dia = fechacompleta.getDate();
    var mes = fechacompleta.getMonth();
    mes += 1
    var anyo = fechacompleta.getFullYear();
    var mt = "AM";
    // Pongo el formato 12 horas
    if (horas > 12) {
        mt = "PM";
        horas = horas - 12;
    }
    if (horas == 0) horas = 12;
    // Pongo minutos y segundos con dos dígitos
    if (minutos <= 9) minutos = "0" + minutos;
    if (segundos <= 9) segundos = "0" + segundos;
    // En la variable 'cadenareloj' puedes cambiar los colores y el tipo de fuente
    cadenareloj = "<font size='2' face='verdana' ><b>" + horas + ":" + minutos + ":" + segundos + " " + mt + " <br/> "+ dia +"/"+ mes +"/"+ anyo + "</b></font>";
    // Se escribe el reloj de una manera u otra, según el navegador del usuario
    if (document.layers) {
        document.layers.spanreloj.document.write(cadenareloj);
        document.layers.spanreloj.document.close();
    }
    else if (document.all) spanreloj.innerHTML = cadenareloj;
    else if (document.getElementById) document.getElementById("spanreloj").innerHTML = cadenareloj;
    // Se ejecuta la función con un intervalo de un segundo
    setTimeout("muestraReloj()", 1000);
}

function fSubmit(){
    document.forms.posicion.pagina.submit();
}

function handleEnter (field, event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        fSubmit();
        return false;
    }
    else
        return true;
} 

    
var bt = document.getElementById("miboton");
bt.addEventListener("click", fechaValida(), false);


//
//var nuevo = document.getElementById("titulo"); 
//nuevo.focus(); 