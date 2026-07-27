import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Scanner;

/*
 * コンソール入力でブラウザを起動するプログラム
 *
 * ■コマンド仕様
 * g+エンターキー: Googleを開く、もしくは、g +「空白を好きなだけ挟む」+検索ワード+エンターキー　で検索結果を起動
 * y+エンターキー: YouTubeを開く、もしくはｙ+「空白を好きなだけ挟む」+検索ワード+エンターキー　で検索結果を起動
 * その他: 無効入力としてエラーメッセージを表示
 * 1回切りで終了するプログラム
 * 
 * 
 * 
 * 
 *
 * ■前提
 * Desktop APIが利用可能な環境で動作
 *
 * ■制約
 * ・ｇとｙは、入力はそれぞれ1文字のみ想定
 * ・大文字入力には未対応
 * ・小文字のみ対応　半角小文字と全角小文字に対応
 */

/* =========================
 * エントリーポイント
 * ========================= */
public class BrowserCLI { // ここをBrowserCLIという名前にしました
    public static void main(String[] args) throws Exception {
        System.out.println("コンソール画面にg(Google) または y(YouTube) を入力してEnterしてください。\nもしくは空白を挟んで検索ワードを入れてからエンターキーを押すと、\n検索結果画面になります。");

        /* =========================
         *      安全チェック
         * ========================= 
         * ブラウザ起動が可能な環境かを事前に確認するif文
         *
         * ・Desktop API自体が利用可能か
         * ・BROWSE（ブラウザ起動機能）がサポートされているか
         *
         * どちらかが非対応の場合は処理を中断する
         * （実行時エラー防止）
         *
         * 「この環境がブラウザ起動に対応してるか確認する 2 段階チェック」
         */
        if (!Desktop.isDesktopSupported() ||
            !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
  
            System.out.println("この環境ではブラウザ起動がサポートされていません。");
            return;
        }

        // ここからユーザーから1単語の入力を受け取る流れ（コマンド取得）
        // キーボード入力を監視するScannerクラス（標準ライブラリ）を使用
        // Javaではクラス（設計図）からnew演算子でインスタンス（実体）を生成する必要があるため、
        // ここで変数を用意してnewによる実体化処理を行う
        try (Scanner sc = new Scanner(System.in)) {
        	
        //１回きりのプログラムから、何度も可能なプログラムにするため、真の間は以下の処理をせよという命令を入れる
        	while (true) {
        		 System.out.println("コマンドを入力してください（終了するには eか終了を入力）");
        	
        		// ユーザーの入力を1行まるごと取得し、コマンド部分と検索ワード部分に分割する
        		// nextLine()を使うことで、next()とnextLine()の混在によるバッファの読み残し問題も回避できる
        		String line = sc.nextLine().trim();
            
        		if (line.isEmpty()) {
        		    System.out.println("何も入力されていません。もう一度入力してください。");
        		    continue; // while文の先頭に戻り、再入力を促す
        		}

        		String cmd;
        		String rest;

        		int spaceIndex = line.indexOf(' ');
        		if (spaceIndex == -1) {
        		    // 空白がない場合は入力全体がコマンド
        		    cmd = line;
        		    rest = "";
        		} else {
        		    // 最初の空白より前がコマンド、それ以降が検索ワード
        		    cmd = line.substring(0, spaceIndex);
        		    rest = line.substring(spaceIndex + 1).trim();
        		}
            
        		
            //cmdの機能が定義されたので、この時点でWhile文の終了条件を記述可能
            if (cmd.equals("e") || cmd.equals("ｅ") || cmd.equals("終了")) {
                System.out.println("終了します。");
                break;
            }
            // コマンド（命令）による分岐
            /*

             */
            switch (cmd) {
            case "g":
            case "ｇ":   	
                if (rest.isEmpty()) {
                    Desktop.getDesktop().browse(new URI("https://google.com"));
                } else {
                    String query = URLEncoder.encode(rest, "UTF-8");
                    Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));
                }
                break;
            case "y":
            case "ｙ":
                if (rest.isEmpty()) {
                    Desktop.getDesktop().browse(new URI("https://youtube.com"));
                } else {
                    String query = URLEncoder.encode(rest, "UTF-8");
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query=" + query));
                }
                break;
            default:
                System.out.println("メインの辞書にそのコマンドはありません。");
                break;
        }
    }
  }
        
    }
}
               
