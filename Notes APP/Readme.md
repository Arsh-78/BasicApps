1.Architecture Components
2.LiveModel
3.RoomDB
Industry level app designing rules,android recommended way of designing apps
#Android Components:2017 Google I/O introduction
Components provided:i)Room ii) WorkManager iii)Navigation iv)Lifecycle v)Data Binding vi)Paging
ViewModel:Live data
-> vieModel Comes under MVVM architecture.i.. mvvm model viewmodel ,It divivdes tasks,as Activity just shows ui,View tells the type of data,all the tasks related to data are noow controlled by viewmodeL
Repository is stored in  viewmodel ans is culmonary responsible for all data related communications
 **ROOM** 
 Our backend for an app.its an upgradation over SQL lite,which lets us create table and lets us store data in Entitys (Corresponding to Schema) then we use **DAO** DataAccessObject to access these data
 Any Sequel table querires or Room querires are accessed duing DAO
 Its a kotlin class interface

<h5>Live Data</h5>
Helps us to track our table ,updating as soon as changes are made,included in architecture component,It helps us keep track over our data

**Repository**
Our App may have data from various sources from online ,offline sources so *ROOM Repository* makes a clean API for UI to communicate and act as a single source of truth for handling data

**ViewModel**
All data import and manipulation is separated by activity with the help of ViewModel, it comes integrated automaticaaly with Lifecyle,recreates activity on changing lanscape to horizontal Mode
