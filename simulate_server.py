from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/get_closest_object')
def get_data():
    data = {
            "Objects": [
                {
                    "object_type": "person",
                    "distance_meters": 10
                }
        
            ]
        }
    return jsonify(data)

if __name__ == '__main__':
    app.run(debug=False, port=8008)