# simpleindexing

The project is to design and implement the following -

Given a list of one million <string name, int score> pairs where names are valid Java variable names, write two programs and try to optimize their efficiency:

1. A Construction Program that produces a serializable data structure D
(say JSON or ProtocolBuffer).

2. A Query Server Program that reads in serialized D and then accepts user queries such that for each query s, it responds with the top 10 names (ranked by score) that start with s or contains ‘_s’ (so for example, both “revenue” and “yearly_revenue” match the prefix “rev”). Query answering should run in sub-linear time (in terms of the number of names in the input).


TBD:

Resources
<Pointer to any references/samples used in this project>
Design and Implementation What and How
<Describe your high level design and further assumptions made>
Alternatives
If applicable, did you consider other options? And, why did you not choose those options?
   

Deployment Plan
monitoring, reporting, alerts, etc.

What configuration parameters does this component need? How would administrators set these parameters?

Once the code is deployed in production, we need to ensure that we are able to monitor this component for availability/performance. What metrics/charts would you monitor and report for this component

Logging

Log profusely so that we can identify, diagnose, and fix problems easily post deployment. In this section, define the log format of what you expect to log from this component.

Test Plan (optional)

<What unit and functional tests would you write to verify the component>
  
