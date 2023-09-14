package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {

    ArrayList<Word> list;
    Scanner s;
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
    }
    }
}
