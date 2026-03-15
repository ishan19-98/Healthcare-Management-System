import sys
import pandas as pd
import pickle

model=pickle.load(open('attendance-model.pkl','rb'))

distanceFromClinicKm=int(sys.argv[1])
previousNumberOfAppointments=int(sys.argv[2])
pastNoShowCount=int(sys.argv[3])

df=pd.DataFrame([[distanceFromClinicKm,previousNumberOfAppointments,pastNoShowCount]], columns=[
        "distanceFromClinicKm",
        "previousNumberOfAppointments",
        "pastNoShowCount"
    ])

prediction=model.predict(df)
print(prediction)