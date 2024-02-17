# 実技試験 2

一般的な RESTful API の主な設計原則を満たす、書籍管理 API サーバーを実装・公開してください。

# 実装の詳細
## 概要

下記のエンドポイントを実装してください。

- GET /v1/books -> 全ての書籍情報の一覧を返します
- GET /v1/books/{id} -> 指定した ID の書籍情報を返します
- POST /v1/books -> 指定した書籍情報を登録します
- PATCH /v1/books/{id} -> 指定した ID の書籍情報を更新する
- DELETE /v1/books/{id} -> 指定した ID の書籍情報の削除する

その他明示していない詳細は
[Web API: The Good Parts](https://www.amazon.co.jp/exec/obidos/ASIN/4873116864/shohei0823-22)、および
[RESTful Web API の設計](https://learn.microsoft.com/ja-jp/azure/architecture/best-practices/api-design)
を参考にして実装してください。

データベースのスキーマについては、sql/create.sql を使用し、 PostgreSQL にデータベースを作成してください。  

[Render](https://render.com/) を使用して、アプリをサーバー上にデプロイしてください。  
アプリをデプロイした後、アプリの URL をプロジェクトに用意された test/resources/application.yml の `environment.baseUri` に保存してください。  

```
environment:
  baseUri: http://localhost:8080
```

`mvn test -Dtest=RunCucumberTest` をテスト実行し、実装したものが要求を満たしているか確認ください。  

# 基本仕様
## GET /v1/books エンドポイント

データベースの全ての書籍情報を返します。

### 期待するリクエスト形式:

```
GET /v1/books
```

### 期待するレスポンス形式:

- Header
```
HTTP/1.1 200
```

- Body
```
{
  "books": [
    {
      "id": "1",
      "title": "テスト駆動開発",
      "author": "Kent Beck",
      "publisher": "オーム社",
      "price": 3080
    },
    {
      "id": "2",
      "title": "アジャイルサムライ",
      "author": "Jonathan Rasmusson",
      "publisher": "オーム社",
      "price": 2860
    }
  ]
}
```

## GET /v1/books/{id} エンドポイント

id で指定した書籍情報を返します。

### 期待するリクエスト形式:

```
GET /v1/books/1
```

### 期待するレスポンス形式:

- Header
```
HTTP/1.1 200
```

- Body
```
{
  "id": "1",
  "title": "テスト駆動開発",
  "author": "Kent Beck",
  "publisher": "オーム社",
  "price": 3080
}
```

## POST /v1/books エンドポイント

書籍情報を新規に登録します。

### 期待するリクエスト形式: 

```
POST /v1/books
```

- Body
```
{
  "title": "Clean Agile",
  "author": "Robert C. Martin",
  "publisher": "ドワンゴ",
  "price": 2640
}
```

### 期待するレスポンス形式:

** 成功レスポンス: **

- Header
```
HTTP/1.1 201
Location: http://host.domain:port/v1/books/3
```

** 失敗レスポンス: **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0001",
  "message": "request validation error is occurred.",
  "details": [
    "title must not be blank"
  ]  
}
```


## PATCH /v1/books/{id} エンドポイント

id で指定した書籍情報を更新します。

### 期待するリクエスト形式: 

```
PATCH /v1/books/1
```

- Body
```
{
  "author": "Uncle Bob"
}
```


### 期待するレスポンス形式:

** 成功レスポンス: **
- Header
```
HTTP/1.1 204
```

** 失敗レスポンス (id で指定された書籍情報が存在しない場合): **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0002",
  "message": "specified book [id = 4] is not found.",
  "details": []
}
```
  
## DELETE /v1/books/{id} エンドポイント

id で指定した書籍情報を削除します。

### 期待するリクエスト形式:

```
DELETE /v1/books/1
```

### 期待するレスポンス形式:

** 成功レスポンス: **
- Header
```
HTTP/1.1 204
```

** 失敗レスポンス (id で指定された書籍情報が存在しない場合): **
- Header
```
HTTP/1.1 400
```

- Body
```
{
  "code": "0002",
  "message": "specified book [id = 4] is not found.",
  "details": []
}
```
