import matplotlib.pyplot as plt
import pandas as pd

# Data extracted from the screenshots for quick Sort
heap_data = {
    "Size": [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200],
    "Avg Count": [444, 1106, 1732, 2251, 2282, 3994, 4140, 4026, 6177, 6767, 7593, 7434],
    "Avg Time": [601469198188240, 601469204634492, 601469210182090, 601469215109037, 601469222080845,
                 601469229230407, 601469236241390, 601469246458457, 601469259498192, 601469268889950,
                 601469277333697, 601469288255595]
}

# Data extracted from the screenshots for heap Sort
quick_data = {
    "Size": [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200],
    "Avg Count": [444, 1106, 1732, 2251, 2282, 3994, 4140, 4026, 6177, 6767, 7593, 7434],
    "Avg Time": [44777427.5, 2637185.0, 1577347.5, 1575652.5, 2334827.5, 1658832.5, 1872240.0,
                 1170832.5, 1368070.0, 1018617.5, 1280917.5, 1043647.5]
}

# Convert dictionaries to pandas DataFrames for easier handling
heap_df = pd.DataFrame(heap_data)
quick_df = pd.DataFrame(quick_data)

# Plotting Critical Operations
plt.figure(figsize=(14, 7))
plt.plot(heap_df["Size"], heap_df["Avg Count"], label='Insertion Sort - Critical Operations', marker='o')
plt.plot(quick_df["Size"], quick_df["Avg Count"], label='Merge Sort - Critical Operations', marker='o')
plt.title('Critical Operations vs Input Size')
plt.xlabel('Input Size')
plt.ylabel('Number of Critical Operations')
plt.legend()
plt.grid(True)
# move plot to the right to avoid overlapping with the ylabels
plt.subplots_adjust(left=0.2)
# move the ylabels to the left to avoid overlapping with the plot
plt.tick_params(axis='y', pad=35)
plt.savefig('critical_operations.png')

# Plotting Execution Times
plt.figure(figsize=(14, 7))
plt.plot(heap_df["Size"], heap_df["Avg Time"], label='Heap Sort - Execution Times', marker='o')
plt.plot(quick_df["Size"], quick_df["Avg Time"], label='Quick Sort - Execution Times', marker='o')
plt.title('Execution Times vs Input Size')
plt.xlabel('Input Size')
plt.ylabel('Execution Time (ms)')
plt.legend()
plt.grid(True)
plt.subplots_adjust(left=0.2)
# move the ylabels to the left to avoid overlapping with the plot
plt.tick_params(axis='y', pad=35)
plt.savefig('execution_times.png')