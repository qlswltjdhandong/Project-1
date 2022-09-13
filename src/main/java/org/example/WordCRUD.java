package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        return new Word(0,level,word,meaning);
    }
    public void addWordTOList(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {
    }

    public void listAll(){
        System.out.println("------------------------");
        for(int i=0; i< list.size(); i++){
            System.out.print(i+1 + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("------------------------");
    }
    public ArrayList<Integer> listAll(String key){
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("------------------------");
        int k=0;
        for(int i=0; i< list.size(); i++){
            String word = list.get(i).getWord();
            if(!word.contains(key)) continue;
            System.out.print(k+1 + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            k++;
        }
        System.out.println("------------------------");
        return idlist;
    }
    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String key = s.next();
        ArrayList<Integer> idlist = this.listAll(key);
        System.out.print("=> 수정할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(num-1));
        word.setMeaning(meaning);
        System.out.print("단어가 수정되었습니다.");

    }
}

