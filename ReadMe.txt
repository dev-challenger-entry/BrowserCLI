Javaアプリ学習リポジトリ

Javaを用いたアプリ開発の手順をあとから復習できるようにするために作成したリポジトリです。

学習内容：
- Eclipse（Pleiades日本語版）での開発
　Javaのバージョンは21


■動作確認手順

【Eclipseで実行する場合】
■前提：クローン直後の状態について

このリポジトリは以下を .gitignore で除外しています。
bin/, .classpath, .project, .settings/（Eclipseが自動生成するファイル）
*.class, target/（ビルドで生成されるファイル）

　そのため git clone しただけでは、Eclipseでそのまま開けるような
「プロジェクト」にはなっていません。
　初回のみ、下記のセットアップが必要です。

【Eclipseの場合】
　　https://willbrains.jp/index.html#/pleiades_distros2025.html
　　これでJava言語版をダウンロードする。
　　ダウンロードした自己解凍書庫ファイルを解凍して、セットアップ完了です。
　　その後、アプリアイコンをクリックし起動します。

1. 「ファイル」→「新規」→「Javaプロジェクト」
2. プロジェクト名を入力（例：GoogleStart）、プロジェクト名はなんでも可。
　　実行環境はJava 21を選択
3. 「完了」を押すと .project / .classpath / bin が自動生成される
4. 　Eclipseで、ファイル→新規→クラス→BrowserCLIと入れる
　　　　クローンしたリポジトリの src フォルダの中身を、
  　　 新規作成したプロジェクトの src フォルダにコピーする
　　　※行き詰ったときはエクスプローラーを開いて移動させると分かりやすいかもしれません。
　　　※エクスプローラー（外部）を使ってファイルをコピーした場合は、プロジェクトを右クリック→「リフレッシュ」してソースを認識させる
5. Eclipseの画面内で、srcフォルダ内へ、コピー＆ペーストした場合はリフレッシュは不要のためこれで準備は終了です

【ターミナルの場合】
　特別なセットアップは不要（javac / java コマンドで直接実行可能）

■動作確認手順（セットアップ後・2回目以降も共通）

【Eclipseで実行する場合】
1. GoogleStart.java を右クリック → 「実行」→「Java アプリケーション」を選択
2. コンソールビューに「メインの入り口です。g(Google) または y(YouTube) を
   入力してください。」と表示される
3. コンソール上で g または y を入力してEnter
4. ブラウザでGoogle（またはYouTube）が起動すれば成功
5. 一回きりのプログラムなので、もう１度YouTube等を使いたいときは「実行」からやり直す

【ターミナルで実行する場合】
1. ターミナル（コマンドプロンプト／PowerShell）を開く
2. GoogleStart.java があるフォルダに移動する
   例：cd C:\Users\xxxx\workspace\GoogleStart\src
3. javac GoogleStart.java
   → 同じフォルダに GoogleStart.class が生成される（.gitignoreで除外対象）
4. java GoogleStart
5. g または y を入力してEnter
6. ブラウザでGoogle（またはYouTube）が起動すれば成功
7．一回きりのプログラムなので、もう一度プログラムを実行したいときは、4の手順からやり直す

■補足
・Eclipseはコンパイル済みの .class を bin フォルダに自動生成するが、
  これは .gitignore で除外されているため、他の環境でクローンした際は
  必ずコンパイルし直す必要がある

・.project や .classpath も同様に除外されているため、
  Eclipseで開く際は「既存プロジェクトのインポート」ではなく
  「新規プロジェクト作成＋ソースのコピー」が必要

・リモートデスクトップやSSHなどGUIのない環境では、
  Desktop.isDesktopSupported() が false となりブラウザ起動は行われない
 
