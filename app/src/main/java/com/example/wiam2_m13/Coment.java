package com.example.wiam2_m13;

import java.util.Date;

class Coment {
   String username;
   String date;
   String cuerpoDelComentario;
   int imgUrl;

   public Coment(String username, String date, String cuerpoDelComentario, int imgUrl) {
      this.username = username;
      this.date = date;
      this.cuerpoDelComentario = cuerpoDelComentario;
      this.imgUrl = imgUrl;
   }
}