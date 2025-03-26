<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(45deg, #FFE580, #90EE90);
            text-align: center;
            padding: 20px;
        }
       
        .confirmation-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }
        .message {
            font-size: 2rem;
            font-weight: bold;
            color: #2e8b57;
            margin-bottom: 20px;
        }
        .bike-animation {
            position: relative;
            width: 100%;
            overflow: hidden;
            height: 300px;
            background-color: #f0f0f0;
            border-radius: 10px;
            margin: 20px 0;
        }
        .bike {
            position: absolute;
            bottom: 10px;
            left: -150px;
            width: 200px;
            animation: ride 5s linear infinite;
        }
        @keyframes ride {
            0% { left: -150px; }
            100% { left: 100%; }
        }
        .thank-you {
            font-size: 1.2rem;
            color: #444;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    
    <div class="confirmation-container">
        <div class="message">Your Order is Confirmed!</div>
        <div class="bike-animation">
            <img src="images/download.jpg" alt="Delivery Bike" class="bike">
        </div>
        <p>Our delivery partner is on the way to deliver your delicious meal!</p>
    </div>
</body>
</html>
