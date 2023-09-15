package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {

    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        return new Word(0, level, word, meaning);
    }
    public void addWord(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 추가되었습니다.");
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
        System.out.println("-------------------------------------");
        for(int i=0; i< list.size(); i++){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-------------------------------------");
        System.out.println();
    }
    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j=0;
        System.out.println("-------------------------------------");
        for(int i=0; i< list.size(); i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("-------------------------------------");
        return idlist;
    }


    public void updateWord() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int modifyNumber = s.nextInt();
        System.out.print("=> 뜻 입력 : ");
        s.nextLine();
        String meaning = s.nextLine();
        Word word = list.get(idList.get(modifyNumber-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
        System.out.println();
    }

    public void deleteWord() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int deleteNumber = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/N) : ");
        String real = s.next();
        if(real.equalsIgnoreCase("Y")){
            list.remove((int)idList.get(deleteNumber-1));
            System.out.println("단어가 삭제되었습니다.");
            System.out.println();
        }else{
            System.out.println("취소 되었습니다.");
            System.out.println();
        }

    }

    public void search() {
        System.out.print("=> 검색할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.println();
    }

    public void searchLevel() {
        System.out.print("=> 검색할 레벨 : ");
        int level = s.nextInt();
        int count =0;
        System.out.println("-------------------------------------");
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getLevel()== level){
                System.out.print((count+1) + " ");
                System.out.println(list.get(i).toString());
                count++;
            }
        }
        System.out.println("-------------------------------------");
        System.out.println();
    }
    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count=0;
            while (true) {
                line = br.readLine();
                if(line==null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0,level,word,meaning));
                count++;
            }
            br.close();
            System.out.println("==>" + count + "개 로딩 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list){
                pr.write(one.toFileString()+"\n");
            }

            pr.close();
            System.out.println("==> 데이터 저장 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
