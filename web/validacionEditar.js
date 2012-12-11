/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Verifica() { 
    var nTot=0; 
    var nPas=0; 
    var nTorna=0; 
    sError="Lista de errores: "+"\n"; 
    for (var j=0; j<4; j++) { 
        nEle=j;

        // AVERIGUA LOS TIPOS 
        var sNom=document.forms[1].elements[j].name; 
        var sOne=sNom.substring(0,1); 
        var sTwo=sNom.substring(1,2); 
 

        // CADENA Y OBLIGATORIA 
        if (sOne=='t' && sTwo=='i') { 
            CaracterNoValid(document.forms[1].elements[j].value,'ti'); 
            nTot+=swOK; 
        }
        // CADENA Y OBLIGATORIA  
        if (sOne=='c' && sTwo=='o') { 
            CaracterNoValid(document.forms[1].elements[j].value,'co'); 
            nTot+=swOK; 
        } 

        // CADENA Y OBLIGATORIA 
        if (sOne=='e' && sTwo=='t') { 
            CaracterNoValid(document.forms[1].elements[j].value,'et'); 
            nTot+=swOK; 
        }
     
        // FECHA Y OBLIGATORIA 
        if (sOne=='f' && sTwo=='e') { 
            CaracterNoValid(document.forms[1].elements[j].value,'fe'); 
            nTot+=swOK; 
        }

        // LISTA DE ERRORES 
        if (nPas==0 && nTot>0) { 
            document.forms[1].elements[nEle].focus() 
            nPas=1 
        } 
    }

    if (nTot>0) {
        alert(sError);
        return false;
    }
    else 
        //     document.forms[1].submit(); 
        return true;
}

// ANALIZA CAMPO A CAMPO SI SON NUMERICOS 
//========================================= 
function CaracterNoValid(pCaracter,pType) { 
    swOK=0; 
    var prim = pCaracter.substring(0,1);
    // CADENA 
    if (pType=='ti') {  
        if (pCaracter=='' || / /.test(prim)) { 
            sError+="Campo "+document.forms[1].elements[nEle].name+" ha de ser texto sin espacios al principio y es obligatorio"+"\r" 
            swOK=1; 
            return;
        } 
    } 
    if (pType=='et' ) {  
        if (pCaracter=='' || / /.test(prim)) { 
            sError+="Campo "+document.forms[1].elements[nEle].name+" ha de ser texto sin espacios al principio y es obligatorio"+"\r" 
            swOK=1; 
            return;
        } 
    } 
    if (pType=='co') {  
        if (pCaracter=='' || / /.test(prim)) { 
            sError+="Campo "+document.forms[1].elements[nEle].name+" ha de ser texto sin espacios al principio y es obligatorio"+"\r" 
            swOK=1; 
            return;
        } 
    }
   
   //FECHA
    if (pType=='fe') {  
        if (pCaracter=='' || / /.test(prim)) { 
            sError+="Campo "+document.forms[1].elements[nEle].name+" ha de ser una fecha sin espacios al principio y es obligatorio"+"\r" 
            swOK=1; 
            return;
        }else{
            fechaValida(pCaracter);
            return;
        }
    } 
}
function fechaValida(fecha){
    swOK=0;
    var esBisiesto = (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) ? 1 : 0;
    if (fecha != undefined && fecha != "" ){
        if (!/^\d{4}\-\d{2}\-\d{2}$/.test(fecha)){
            sError+="La fecha "+fecha+" no es válida.  (aaaa-mm-dd)";
            swOK=1;
            return;
        }
    }
    var ano  =  parseInt(fecha.substring(0,4),10);
    var mes  =  parseInt(fecha.substring(5,7),10);
    var dia =   parseInt(fecha.substring(8),10);
 
    switch(mes){
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            numDias=31;
            break;
        case 4: case 6: case 9: case 11:
            numDias=30;
            break;
        case 2:
            if (esBisiesto)
            {
                numDias=29
            }else{
                numDias=28
                };
            break;
        default:
            sError+="Día erróneo";
            swOK=1;
            return false;
            break;
    }
 
    if (dia>numDias || dia==0){
        sError+="Día erróneo";
        swOK=1;
        return false;
			
    }
}