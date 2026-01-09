from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/analyze', methods=['POST'])
def analyze_account():
    data = request.json
    username = data.get('username', 'User')
    balance = data.get('balance', 0.0)

    # Smart Logic: Give advice based on wealth
    if balance < 1000:
        advice = "⚠️ Critical: Your balance is low. Avoid unnecessary expenses."
    elif balance < 10000:
        advice = "✅ Stable: You are doing okay, but try to save 20% of your income."
    else:
        advice = "🚀 Excellent: You have high liquidity. Consider investing in Mutual Funds."

    return jsonify({
        "analysis": f"Hello {username}, I have analyzed your finances.",
        "recommendation": advice
    })

if __name__ == '__main__':
    # Keep it on Port 5001 (Mac Friendly)
    app.run(port=5001)