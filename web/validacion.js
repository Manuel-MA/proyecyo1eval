/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Verifica() { 
   var nTot=0; 
   var nPas=0; 
   var nTorna=0; 
   sError="Lista de errores: "+"\n"; 
   for (var j=0; j<3; j++) { 
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
}

