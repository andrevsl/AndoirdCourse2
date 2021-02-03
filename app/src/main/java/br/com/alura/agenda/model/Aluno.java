package br.com.alura.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Parcelable {
    private  int id=0;
    private  String nome;
    private  String telefone;
    private  String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;

        this.email = email;
    }
    public  Aluno(Parcel p){
        id=p.readInt();
        nome=p.readString();
        telefone= p.readString();
        email=p.readString();
    }
    public Aluno() {


    }
    public static final Creator<Aluno> CREATOR
            = new Creator<Aluno>() {
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(nome);
        out.writeString(telefone);
        out.writeString(email);
    }
    @NonNull
    @Override
    public String toString() {
        return nome;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome= nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone= telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email= email;
    }

    public boolean temIdValido() {
        return id>0;
    }
}
