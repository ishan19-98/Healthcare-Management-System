import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score,confusion_matrix
import pickle

#Load Dataset
df=pd.read_csv('src/main/resources/patient_appointment_dataset.csv')

#Feature Splitting
X=df[['distanceFromClinicKm','previousNumberOfAppointments','pastNoShowCount']]
y=df['attended']

#Split Features
X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=42)

#Logistic Regression Model Creation
model=LogisticRegression()


#Train Model
model.fit(X_train,y_train)

y_pred = model.predict(X_test)

#Model Metrics
print("Accuracy:", accuracy_score(y_test, y_pred))

y_pred = model.predict(X_test)

print(confusion_matrix(y_test, y_pred))

#Storing the model in a pickle file
pickle.dump(model,open('attendance-model.pkl','wb'))