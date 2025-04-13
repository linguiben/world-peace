# pipenv install pandas matplotlib # 安装依赖包
try:
    import os
    import pandas as pd
    import matplotlib.pyplot as plt
except ImportError as e:
    print(f"缺少依赖库: {e.name}")
    print("请运行以下命令安装依赖：")
    print("pipenv install pandas matplotlib")
    exit(1)

country="cCountry"
# raw data download: https://population.un.org/wpp/downloads?folder=Standard%20Projections&group=Mortality
# read data from aCountry.csv，
data_path = os.path.join(os.path.dirname(__file__), "data", country+".csv")
# filter the line starts with #
df = pd.read_csv(data_path, sep="\t",comment="#")


half_total = sum(df["SubTotal"]) / 2
# 将df["SubTotal"]的前temp_age个数累加起来
median_total = 0
median_total_pre = 0
median_age = 0
for i in range(100):
    median_total += df["SubTotal"][i]
    if median_total >= half_total:  # 累加到大于等于half_total
        median_age = i
        break
    else:
        median_total_pre = median_total

# the median age of total death
median_age = median_age + (half_total - median_total_pre) / df["SubTotal"][median_age]
# the Average age of total death
mean_age = sum(df["Age"] * df["SubTotal"]) / sum(df["SubTotal"])
mean_age_male = sum(df["Age"] * df["Male"]) / sum(df["Male"])
mean_age_female = sum(df["Age"] * df["Female"]) / sum(df["Female"])

# Plot gender-specific death distributions
plt.figure(figsize=(10, 6))
plt.plot(df["Age"], df["SubTotal"], color="green", label="Total Deaths")
# plt.plot(df["Age"], df["Male"], color="blue", label="Male Deaths")
# plt.plot(df["Age"], df["Female"], color="pink", label="Female Deaths")
plt.axvline(mean_age, color="green", linestyle="--", label=f"Mean ≈ {mean_age:.1f} yrs")
plt.axvline(median_age, color="red", linestyle="--", label=f"Median ≈ {median_age:.1f} yrs")

plt.title("Age-at-Death Distribution of " + country)
plt.xlabel("Age (Years)")
plt.ylabel("Number of Deaths (×10,000)")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()
