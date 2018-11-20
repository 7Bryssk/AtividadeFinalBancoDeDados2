/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.dao;

/**
 *
 * @author Bruno
 */
public class UsuarioLogado {
    static int idusuario;
    static String Nome;

    public static int getIdusuario() {
        return idusuario;
    }

    public static void setIdusuario(int aIdusuario) {
        idusuario = aIdusuario;
    }

    public static String getNome() {
        return Nome;
    }

    public static void setNome(String aNome) {
        Nome = aNome;
    }
}
