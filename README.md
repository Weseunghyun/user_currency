# User_Currency 프로젝트

## 목차

- [API 명세서](#api-명세서)
- [ERD](#ERD)
- [질문](#질문)

## API 명세서

### 1. 유저 관련 기능

### 1.1. 유저 생성 (POST /users)

<details>
<summary>상세 내용 펼치기</summary>
  
   - **요청**
        - **Header :**
            - `content-type : application/json`
        - **Body :**
        
        ```json
        {
            "name" : "예시",
            "email" : "asd123@naver.com"
        }
        ```
        
    
  - 응답
        - **Body** :
        
        ```json
        {
            "id": 1,
            "name": "예시",
            "email": "asd123@naver.com"
        }
        ```
        
</details>

---

### 1.2. 유저 목록 조회 (GET /users)

<details>
<summary>상세 내용 펼치기</summary>
  
   - **요청**
        - **Nothing**
        
   - **응답**
        - **Body** :
    
    ```json
    [
        {
            "id": 1,
            "name": "예시",
            "email": "asd123@naver.com"
        },
        {
            "id": 2,
            "name": "두번째",
            "email": "efh123@naver.com"
        }
    ]
    ```
    
</details>

---

### 1.3 특정 유저 정보 조회 (GET /users/{userId})

<details>
<summary>상세 내용 펼치기</summary>
  
   - **요청**
        - **PathVariable :**
            - `Long userId`
  - 응답
        - **Body** :
        
        ```json
        {
            "id": 1,
            "name": "예시",
            "email": "asd123@naver.com"
        }
        ```
        
</details>

---

### 1.4. 특정 유저 삭제 (DELETE /users/{userId})

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **PathVariable :**
            - `Long userId`
  - **응답**
        - **Body** :
        
        ```json
        정상적으로 삭제되었습니다.
        ```
        
</details>

---

### 2. 통화 관련 기능

### 2.1. 통화 생성 (POST /currencies)

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **Header :**
            - `content-type : application/json`
        - **Body :**
        
        ```json
        {
            "currencyName" : "USD",
            "exchangeRate" : 1390.91,
            "symbol" : "$"
        }
        ```
        
    
- **응답**
        - **Body** :
        
        ```json
        {
            "id": 1,
            "currencyName": "USD",
            "exchangeRate": 1390.91,
            "symbol": "$"
        }
        ```
        
</details>

---

### 2.2. 통화 목록 조회 (GET /currencies)

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **Nothing**
        
  - **응답**
        - **Body** :
    
    ```json
    [
        {
            "id": 1,
            "currencyName": "USD",
            "exchangeRate": 1390.91,
            "symbol": "$"
        },
        {
            "id": 2,
            "currencyName": "JPY",
            "exchangeRate": 9.18,
            "symbol": "円"
        },
        {
            "id": 3,
            "currencyName": "EUR",
            "exchangeRate": 1470.43,
            "symbol": "€"
        }
    ]
    ```
    
</details>

---

### 2.3. 특정 통화 정보 조회 (GET /currencies/{currencyId})

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **PathVariable :**
            - `Long currencyId`
  - **응답**
        - **Body** :
        
        ```json
        {
            "id": 1,
            "currencyName": "USD",
            "exchangeRate": 1390.91,
            "symbol": "$"
        }
        ```

</details>

---

### 3. 환전 요청 관련 기능

### 3.1. 환전 요청 수행 (POST /exchange-requests)

<details>
<summary>상세 내용 펼치기</summary>
  
   - **요청**
        - **Header :**
            - `content-type : application/json`
        - **Body :**
        
        ```json
        {
        		"userId" : 1,
        		"currencyId" : 3,
        		"amountInKrw": 10000
        }
        ```
        
    
  - 응답
        - **Body** :
        
        ```json
        {
            "id": 3,
            "userId": 1,
            "currencyId": 3,
            "amountInKrw": 10000,
            "formattedAmountAfterExchange": "6.8 €",
            "userCurrencyStatus": "NORMAL",
            "createdAt": "2024-11-28T16:41:21.1249799",
            "modifiedAt": "2024-11-28T16:41:21.1249799"
        }
        ```
        
</details>

---

### 3.2. 특정 유저 환전 요청 조회 (GET /exchange-requests/{userId})

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **PathVariable:**
            - `Long userId`
    
  - **응답**
        - **Body** :
        
        ```json
        [
            {
                "id": 2,
                "userId": 1,
                "currencyId": 2,
                "amountInKrw": 10000.00,
                "formattedAmountAfterExchange": "7.19 $",
                "userCurrencyStatus": "NORMAL",
                "createdAt": "2024-11-28T16:41:17.627981",
                "modifiedAt": "2024-11-28T16:41:17.627981"
            },
            {
                "id": 3,
                "userId": 1,
                "currencyId": 3,
                "amountInKrw": 10000.00,
                "formattedAmountAfterExchange": "6.8 €",
                "userCurrencyStatus": "NORMAL",
                "createdAt": "2024-11-28T16:41:21.12498",
                "modifiedAt": "2024-11-28T16:41:21.12498"
            }
        ]
        ```
        
</details>

---

### 3.3. 특정 유저 환전 그룹화 정보 조회 (GET /exchange-requests/group/{userId})

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **PathVariable:**
            - `Long userId`
    
  - **응답**
        - **Body** :
        
        ```json
        {
            "count": 2,
            "totalAmountInKrw": 20000.00
        }
        ```

</details>

---

### 3.4. 특정 환전 요청 업데이트 (PATCH  /exchange-requests/{userCurrencyId})

<details>
<summary>상세 내용 펼치기</summary>
  
  - **요청**
        - **PathVariable:**
            - `Long userId`
    
  - **응답**
        - **Body** :
        
        ```json
        {
            "id": 1,
            "userId": 1,
            "currencyId": 2,
            "amountInKrw": 10000.00,
            "formattedAmountAfterExchange": "1089 円",
            "userCurrencyStatus": "CANCELED",
            "createdAt": "2024-11-28T16:41:14.46799",
            "modifiedAt": "2024-11-28T16:41:29.3015931"
        }
        ```

</details>

---

## ERD

![image](https://github.com/user-attachments/assets/0f527072-10c6-40cf-8bbe-7c6146707277)


---

## 질문

1. 생성자를 사용하는 것과 builder를 사용하는 것. 
    
    실제로 프로젝트를 진행하면서 어떨 때 어느 것을 써야 좋은 건지 아직 잘 감이 오지 않아서
    
    이번 개인 프로젝트에서는 두 가지 방식을 혼합하여 사용해보았습니다.
    
2. 좋은 주석이란 무엇일까?
    
    주석을 적는 다양한 방식이 존재하는데 JavaDoc 작성법을 남용하면 좋은 것 같지 않아
    
    필요하다고 생각하는 곳에만 적고
    
    간결하게 설명이 필요하다고 생각하는 곳에는 단일 주석을 적었는데
    
    주석을 잘 적고 있는건지 아직 잘 모르겠습니다! 
    

1. `ExchangeService`클래스의  `buildExchangeResponseDto` 메서드
    
    요청 생성, 요청 업데이트 기능에서 중복되는 모습이 보여 따로 private 메서드로 분리했는데
    
    해당 코드를 더 잘 수행할 것 같은 객체에게 위임하거나 객체를 만들어 메서드를 분리해야한다는 말을 보았습니다.
    
    이미 `ExchangeResponseDto` 내부에서 구현해놓은 toDto (생성자를 이용한 것) 메서드가 있지만
    
    builder를 사용해보기 위해 따로 builder를 사용하는 메서드도 구현했습니다.
    
    이렇게 객체를 Dto로 변환하는 메서드는
    
    static을 사용해서 변환될 Dto 내부에 두는 것이 좋을까요
    
    private 메서드로 따로 빼서 service 클래스 내에서 해결하는게 좋을까요?
    
    `ExchangeResponseDto` 내부 `toDto` 메서드는 `findExchangeRequests` 메서드에서 사용
    
    `ExchangeService` 내부  `buildExchangeResponseDto` 메서드는 
    
    `createExchangeRequest` , `updateExchangeRequest` 메서드에서 사용
