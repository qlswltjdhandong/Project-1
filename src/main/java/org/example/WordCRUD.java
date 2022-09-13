package org.example;

import java.io.*;
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
    public void addItem(){
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
    public void listAll(int level){
        System.out.println("------------------------");
        int k=0;
        for(int i=0; i< list.size(); i++){
            int slevel = list.get(i).getLevel();
            if(!(slevel==level)) continue;
            System.out.print(k+1 + " ");
            System.out.println(list.get(i).toString());
            k++;
        }
        System.out.println("------------------------");
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

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String key = s.next();
        ArrayList<Integer> idlist = this.listAll(key);
        System.out.print("=> 삭제할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/n)");
        String answer = s.next();
        if(answer.equalsIgnoreCase("Y")){
           list.remove((int)idlist.get(num-1));//remove 안 integer
           System.out.println("단어가 삭제되었습니다.");
        }
        else{
            System.out.println("최소되었습니다.");
        }

    }

    public void saveFile(){
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for(Word one : list){
                pr.write(one.toFileString());
                pr.write("\n");
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료 !!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            String line = null;
            int count=0;
            while(true){
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0,level,word,meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!!!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.print("==> 원하는 레벨은? (1~3)");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("==> 원하는 단어는? ");
        String key = s.next();
        listAll(key);
    }
}

